package spa.lyh.cn.lib_utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class TimeUtils {
    private final static long second = 1000;
    private final static long minute = 60 * 1000;// 1分钟
    private final static long hour = 60 * minute;// 1小时
    private final static long day = 24 * hour;// 1天

    /**
     * 毫秒转换分：秒
     * @param time
     * @return
     */
    public static String getGapTime(long time){
        long minutes = time / (1000 * 60);
        long second = (time-minutes*(1000 * 60))/(1000);
        String diffTime;
        if (minutes<10){
            diffTime="0"+minutes;
        }else {
            diffTime=""+minutes;
        }
        if(second<10){
            diffTime=diffTime+":0"+second;
        }else{
            diffTime=diffTime+":"+second;
        }
        return diffTime;
    }

    /**
     * 时间戳转多少天前的格式
     * @return
     */
    public static String getShowTime(long timeSeconds){
//        long diff = getLongToString(getLosAngelesTime()) -timeSeconds;  //0;//当前时间 - 得到时间
        long time = System.currentTimeMillis();
        long diff = time - timeSeconds;  //0;//当前时间 - 得到时间
        long r= 0;

        if (diff > second){
            r = (diff /second);
            if (r < 59){//大于秒
                return  r +"秒前";
            }
        }

        if (diff > minute){
            r = (diff / minute);
            if (r < 59){
                return  r +"分钟前";
            }
        }

        if (diff > hour){
            r = (diff / hour);
            if (r < 24){
                return  r + "小时前";
            }
        }

        return getCurrentTimeToString(timeSeconds,"yyyy-MM-dd");
    }

    /**
     * 将时间戳转换成String
     * @param timeSeconds
     * @return
     */
    public static String getCurrentTimeToString(long timeSeconds,String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,
                Locale.getDefault());
        return sdf.format(timeSeconds);
    }
}
