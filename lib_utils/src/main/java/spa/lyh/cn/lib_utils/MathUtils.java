package spa.lyh.cn.lib_utils;

public class MathUtils {

    /**
     * float转int四舍五入
     * @param f
     * @return
     */
    public static int floatToInt(float f){
        int i = 0;
        if(f > 0){
            //正数
            i = (int) ((f*10 + 5)/10);
        } else if(f < 0){
            //负数
            i = (int) ((f*10 - 5)/10);
        }
        return i;
    }
}
