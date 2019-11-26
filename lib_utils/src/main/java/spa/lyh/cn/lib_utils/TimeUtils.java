package spa.lyh.cn.lib_utils;

public class TimeUtils {

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
}
