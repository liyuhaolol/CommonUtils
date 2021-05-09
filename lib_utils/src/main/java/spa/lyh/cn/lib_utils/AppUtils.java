package spa.lyh.cn.lib_utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.DisplayCutout;
import android.view.View;
import android.view.Window;
import android.view.WindowInsets;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.List;

import spa.lyh.cn.lib_utils.listener.NotchListener;

public class AppUtils {

    /**
     * 判断当前应用是否是debug状态
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断手机是否存在导航栏
     * @param context
     * @return
     */
    public static boolean checkDeviceHasNavigationBar(Context context) {
       //通过判断设备是否有返回键、菜单键(不是虚拟键,是手机屏幕外的按键)来确定是否有navigation bar
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;
    }

    /** 读取Assets文件夹中的图片资源
     * @param context
     * @param fileName 图片名称
     * @return
     */
    public static Bitmap getImageFromAssetsFile(Context context, String fileName) {
        Bitmap image = null;
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    /** 读取Res文件夹中的图片资源
     * @param context
     * @param resId 图片资源id
     * @return
     */
    public static Bitmap getImageFromRes(Context context, int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP && bitmap == null){
            //当前资源不存在，或者可能是vector,按照vector再获取一次
            Drawable vectorDrawable = context.getDrawable(resId);
            bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(),
                    vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            vectorDrawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            vectorDrawable.draw(canvas);
        }
        return bitmap;
    }

    /**
     * 检查网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable(Context context) {
        if(context == null)  return false;
        /*ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkCapabilities capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
            if (capabilities != null) {
                *//*if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true;
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true;
                }  else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                    return true;
                }*//*
                return true;
            }
        }
        return false;*/
        //上面的方法只支持M以上，不过这里好像不需要
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 设置显示的样式
     * @param decorView
     * @param visibility
     */
    public static void setSystemUiVisibility(View decorView, int visibility){
        setSystemUiVisibility(decorView,visibility,true);
    }

    /**
     * 设置显示的样式
     * @param decorView
     * @param visibility
     * @param isAddVisibility 是否添加这个属性，true添加，false移除
     */
    public static void setSystemUiVisibility(View decorView,int visibility,boolean isAddVisibility){
        int oldVis = decorView.getSystemUiVisibility();
        int newVis = oldVis;
        if (isAddVisibility){
            newVis |= visibility;
        }else {
            newVis &= ~visibility;
        }
        if (newVis != oldVis) {
            decorView.setSystemUiVisibility(newVis);
        }
    }

    /**
     * 判断是否存在刘海屏等异形屏
     * @param window
     * @param listener
     */
    public static void hasNotch (Window window, final NotchListener listener){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            final View decorView = window.getDecorView();
            decorView.post(new Runnable() {
                @Override
                public void run() {
                    WindowInsets windowInsets = decorView.getRootWindowInsets();
                    DisplayCutout displayCutout = windowInsets.getDisplayCutout();
                    if (displayCutout != null){
                        List<Rect> rects = displayCutout.getBoundingRects();
                        if (rects == null || rects.size() == 0) {
                            listener.onResult(false);
                        }else {
                            listener.onResult(true);
                        }
                    }else {
                        listener.onResult(false);
                    }
                }
            });
        }else {
            //暂时不对Android8.1的非官方API刘海屏进行兼容
            listener.onResult(false);
        }
    }
}
