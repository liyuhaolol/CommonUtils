package spa.lyh.cn.lib_utils.ntp;


import android.content.Context;
import android.os.SystemClock;
import android.util.Log;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import spa.lyh.cn.lib_utils.SPUtils;

public class SntpClient {
    private static final String TAG = "SntpClient";
    private static final boolean DBG = true;

    private static final int REFERENCE_TIME_OFFSET = 16;
    private static final int ORIGINATE_TIME_OFFSET = 24;
    private static final int RECEIVE_TIME_OFFSET = 32;
    private static final int TRANSMIT_TIME_OFFSET = 40;
    private static final int NTP_PACKET_SIZE = 48;

    private static final int NTP_PORT = 123;
    private static final int NTP_MODE_CLIENT = 3;
    private static final int NTP_MODE_SERVER = 4;
    private static final int NTP_MODE_BROADCAST = 5;
    private static final int NTP_VERSION = 3;

    private static final int NTP_LEAP_NOSYNC = 3;
    private static final int NTP_STRATUM_DEATH = 0;
    private static final int NTP_STRATUM_MAX = 15;

    // Number of seconds between Jan 1, 1900 and Jan 1, 1970
    // 70 years plus 17 leap days
    private static final long OFFSET_1900_TO_1970 = ((365L * 70L) + 17L) * 24L * 60L * 60L;

    // system time computed from NTP server response
    private long mNtpTime;

    // value of SystemClock.elapsedRealtime() corresponding to mNtpTime
    private long mNtpTimeReference;

    // round trip time in milliseconds
    private long mRoundTripTime;

    private static class InvalidServerReplyException extends Exception {
        public InvalidServerReplyException(String message) {
            super(message);
        }
    }

    /**
     * Sends an SNTP request to the given host and processes the response.
     *
     * @param host    host name of the server.
     * @param timeout network timeout in milliseconds.
     * @return true if the transaction was successful.
     */
    public boolean requestTime(String host, int timeout) {
        DatagramSocket socket = null;
        InetAddress address = null;
        try {
            address = InetAddress.getByName(host);
            socket = new DatagramSocket();
            socket.setSoTimeout(timeout);
            socket.setBroadcast(true);
            byte[] buffer = new byte[NTP_PACKET_SIZE];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, NTP_PORT);

            // set mode = 3 (client) and version = 3
            // mode is in low 3 bits of first byte
            // version is in bits 3-5 of first byte
            buffer[0] = NTP_MODE_CLIENT | (NTP_VERSION << 3);

            // get current time and write it to the request packet
            final long requestTime = System.currentTimeMillis();
            final long requestTicks = SystemClock.elapsedRealtime();
            writeTimeStamp(buffer, TRANSMIT_TIME_OFFSET, requestTime);

            socket.send(request);

            // read the response
            DatagramPacket response = new DatagramPacket(buffer, buffer.length);
            socket.receive(response);
            final long responseTicks = SystemClock.elapsedRealtime();
            final long responseTime = requestTime + (responseTicks - requestTicks);

            // extract the results
            final byte leap = (byte) ((buffer[0] >> 6) & 0x3);
            final byte mode = (byte) (buffer[0] & 0x7);
            final int stratum = (int) (buffer[1] & 0xff);
            final long originateTime = readTimeStamp(buffer, ORIGINATE_TIME_OFFSET);
            final long receiveTime = readTimeStamp(buffer, RECEIVE_TIME_OFFSET);
            final long transmitTime = readTimeStamp(buffer, TRANSMIT_TIME_OFFSET);

            /* do sanity check according to RFC */
            // TODO: validate originateTime == requestTime.
            checkValidServerReply(leap, mode, stratum, transmitTime);

            long roundTripTime = responseTicks - requestTicks - (transmitTime - receiveTime);
            // receiveTime = originateTime + transit + skew
            // responseTime = transmitTime + transit - skew
            long clockOffset = ((receiveTime - originateTime) + (transmitTime - responseTime))/2;
            //             = ((originateTime + transit + skew - originateTime) +
            //                (transmitTime - (transmitTime + transit - skew)))/2
            //             = ((transit + skew) + (transmitTime - transmitTime - transit + skew))/2
            //             = (transit + skew - transit + skew)/2
            //             = (2 * skew)/2 = skew
            Log.i(TAG,"Request time form ntp server success, " + address.toString() + " ,roundTripTime: " + roundTripTime);
            if (DBG) {
                Log.i(TAG,"round trip: " + roundTripTime + "ms, " +
                        "clock offset: " + clockOffset + "ms");
            }

            // save our results - use the times on this side of the network latency
            // (response rather than request time)
            mNtpTime = responseTime + clockOffset;
            mNtpTimeReference = responseTicks;
            mRoundTripTime = roundTripTime;
            ntpTime = mNtpTime + SystemClock.elapsedRealtime() - mNtpTimeReference;//计算后的ntp校对时间
            systemTime = System.currentTimeMillis();//校对时间
            if (context != null){
                SPUtils.putLong("lyh_ntp_time",ntpTime,context);
                SPUtils.putLong("lyh_sys_time",systemTime,context);
            }
        } catch (Exception e) {
//            if (DBG) {
//                Log.e(TAG, "Error address: " + address.toString());
//            }
            Log.i(TAG,"Request time from ntp server failed ,msg: " + e.getMessage());
            return false;
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

        return true;
    }

    /**
     * Returns the time computed from the NTP transaction.
     *
     * @return time value computed from NTP server response.
     */
    public long getNtpTime() {
        return mNtpTime;
    }

    /**
     * Returns the reference clock value (value of SystemClock.elapsedRealtime())
     * corresponding to the NTP time.
     *
     * @return reference clock corresponding to the NTP time.
     */
    public long getNtpTimeReference() {
        return mNtpTimeReference;
    }

    /**
     * Returns the round trip time of the NTP transaction
     *
     * @return round trip time in milliseconds.
     */
    public long getRoundTripTime() {
        return mRoundTripTime;
    }

    private static void checkValidServerReply(
            byte leap, byte mode, int stratum, long transmitTime)
            throws InvalidServerReplyException {
        if (leap == NTP_LEAP_NOSYNC) {
            throw new InvalidServerReplyException("unsynchronized server");
        }
        if ((mode != NTP_MODE_SERVER) && (mode != NTP_MODE_BROADCAST)) {
            throw new InvalidServerReplyException("untrusted mode: " + mode);
        }
        if ((stratum == NTP_STRATUM_DEATH) || (stratum > NTP_STRATUM_MAX)) {
            throw new InvalidServerReplyException("untrusted stratum: " + stratum);
        }
        if (transmitTime == 0) {
            throw new InvalidServerReplyException("zero transmitTime");
        }
    }

    /**
     * Reads an unsigned 32 bit big endian number from the given offset in the buffer.
     */
    private long read32(byte[] buffer, int offset) {
        byte b0 = buffer[offset];
        byte b1 = buffer[offset + 1];
        byte b2 = buffer[offset + 2];
        byte b3 = buffer[offset + 3];

        // convert signed bytes to unsigned values
        int i0 = ((b0 & 0x80) == 0x80 ? (b0 & 0x7F) + 0x80 : b0);
        int i1 = ((b1 & 0x80) == 0x80 ? (b1 & 0x7F) + 0x80 : b1);
        int i2 = ((b2 & 0x80) == 0x80 ? (b2 & 0x7F) + 0x80 : b2);
        int i3 = ((b3 & 0x80) == 0x80 ? (b3 & 0x7F) + 0x80 : b3);

        return ((long) i0 << 24) + ((long) i1 << 16) + ((long) i2 << 8) + (long) i3;
    }

    /**
     * Reads the NTP time stamp at the given offset in the buffer and returns
     * it as a system time (milliseconds since January 1, 1970).
     */
    private long readTimeStamp(byte[] buffer, int offset) {
        long seconds = read32(buffer, offset);
        long fraction = read32(buffer, offset + 4);
        // Special case: zero means zero.
        if (seconds == 0 && fraction == 0) {
            return 0;
        }
        return ((seconds - OFFSET_1900_TO_1970) * 1000) + ((fraction * 1000L) / 0x100000000L);
    }

    /**
     * Writes system time (milliseconds since January 1, 1970) as an NTP time stamp
     * at the given offset in the buffer.
     */
    private void writeTimeStamp(byte[] buffer, int offset, long time) {
        // Special case: zero means zero.
        if (time == 0) {
            Arrays.fill(buffer, offset, offset + 8, (byte) 0x00);
            return;
        }

        long seconds = time / 1000L;
        long milliseconds = time - seconds * 1000L;
        seconds += OFFSET_1900_TO_1970;

        // write seconds in big endian format
        buffer[offset++] = (byte) (seconds >> 24);
        buffer[offset++] = (byte) (seconds >> 16);
        buffer[offset++] = (byte) (seconds >> 8);
        buffer[offset++] = (byte) (seconds >> 0);

        long fraction = milliseconds * 0x100000000L / 1000L;
        // write fraction in big endian format
        buffer[offset++] = (byte) (fraction >> 24);
        buffer[offset++] = (byte) (fraction >> 16);
        buffer[offset++] = (byte) (fraction >> 8);
        // low order bits should be random data
        buffer[offset++] = (byte) (Math.random() * 255.0);
    }

    /////开始写一些东西
    private long systemTime;//系统时间
    private long ntpTime;
    private static SntpClient instance;
    private boolean isRequest;
    private Context context;

    private static String[] ntpServers = new String[]{
            "ntp.ntsc.ac.cn"
            ,"pool.ntp.org"
            ,"ntp.aliyun.com"
            ,"ntp.tencent.com"
            ,"time.windows.com"
            ,"time.apple.com"
    };

    public static SntpClient getInstance(){
        if (instance == null){
            synchronized (SntpClient.class){
                if (instance == null){
                    instance = new SntpClient();
                }
            }
        }
        return instance;
    }

    //获取时间
    public long getTime(Context cont){
        try{
            if (cont != null){
                this.context = cont.getApplicationContext();
                if (ntpTime > 0){
                    //已存在缓存时间
                    long nowSystemTime = System.currentTimeMillis();//当前系统时间
                    long nowNtpTime = nowSystemTime - systemTime + ntpTime;
                    if ((nowNtpTime - ntpTime) >= 3600000){
                        //时间已经超过1个小时，重新去校对一次时间
                        requestNtpTime();
                    }
                    return nowNtpTime;
                }else {
                    //还没有ntp时间
                    requestNtpTime();
                    ntpTime = SPUtils.getLong("lyh_ntp_time",0,context);
                    systemTime = SPUtils.getLong("lyh_sys_time",0,context);
                    //如果ntptime存在持久化，优先返回持久化，避免竞速广告接口总是失败
                    if (ntpTime > 0 && systemTime > 0){
                        //存在持久化数据
                        Log.e(TAG,"取得持久化的ntp服务器时间");
                        long nowSystemTime = System.currentTimeMillis();//当前系统时间
                        return nowSystemTime - systemTime + ntpTime;
                    }else {
                        return System.currentTimeMillis();
                    }
                }
            }else {
                if (ntpTime > 0){
                    //已存在缓存时间
                    long nowSystemTime = System.currentTimeMillis();//当前系统时间
                    long nowNtpTime = nowSystemTime - systemTime + ntpTime;
                    if ((nowNtpTime - ntpTime) >= 3600000){
                        //时间已经超过1个小时，重新去校对一次时间
                        requestNtpTime();
                    }
                    return nowNtpTime;
                }else {
                    return System.currentTimeMillis();
                }
            }
        }catch (Exception e){
            Log.e(TAG,"获得ntp时间遇到了问题，返回本机时间");
            return System.currentTimeMillis();
        }
    }

    //进行ntp时间请求
    public static void requestNtpTime(){
        if (!SntpClient.getInstance().isRequest){
            ExecutorService threadExecutor = Executors.newSingleThreadExecutor();
            threadExecutor.submit(new NtpTask());
        }else {
            Log.e(TAG,"当前有ntp请求正在进行");
        }
    }

    private static class NtpTask implements Runnable {
        SntpClient client;
        @Override
        public void run() {
            client = SntpClient.getInstance();
            client.isRequest = true;
            for (String host:ntpServers){
                if (client.requestTime(host,2000)){
                    //请求成功，跳出循环
                    Log.e(TAG,"通过"+host+"校对时间成功");
                    break;
                }else {
                    Log.e(TAG,"通过"+host+"校对时间失败");
                }
            }
            client.isRequest = false;
        }
    }
}
