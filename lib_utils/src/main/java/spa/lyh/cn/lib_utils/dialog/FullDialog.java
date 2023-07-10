package spa.lyh.cn.lib_utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import spa.lyh.cn.lib_utils.translucent.BarUtils;
import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
import spa.lyh.cn.lib_utils.translucent.listener.OnNavHeightListener;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;

public abstract class FullDialog extends DialogFragment {
    public final static String TAG = "FullDialog";
    private FragmentActivity act = null;
    private FragmentManager fm = null;

    public String showTag = null;

    public Window window;

    private boolean canCancel = true;

    private View background;

    public Dialog dialog;

    private View statusBar;

    private View navBar;

    private boolean isImmerse = false;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int styleId = setStyleId();
        if (styleId != 0){
            setStyle(STYLE_NO_TITLE,styleId);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        int backgroundId = setBackgroundId();
        if (backgroundId != 0){
            background = view.findViewById(backgroundId);
            background.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isCancelable()){
                        //全局允许取消
                        if (canCancel){
                            //允许点外面取消
                            dismiss();
                        }
                    }
                }
            });
            if (background instanceof ViewGroup){
                ViewGroup rootGroup = (ViewGroup) background; // 将根布局转换为 ViewGroup 对象

                int childCount = rootGroup.getChildCount(); // 获取根布局子视图的数量
                for (int i = 0; i < childCount; i++) {
                    View childView = rootGroup.getChildAt(i); // 获取根布局中的子视图
                    childView.setClickable(true); //将根目录下的一级布局全家变为可点击状态，避免出现点击事件穿透布局的问题
                }
            }
        }
        int statusBarId = setStatusBarId();
        if (statusBarId != 0){
            statusBar = view.findViewById(statusBarId);
        }
        int navBarId = setNavigationBarId();
        if (navBarId != 0){
            navBar = view.findViewById(navBarId);
        }
        isImmerse = isUIimmerseNavbar();
    }

    @Override
    public void onResume() {
        super.onResume();
        dialog = getDialog();
        if (dialog != null){
            dialog.setCanceledOnTouchOutside(canCancel);
            window = dialog.getWindow();
            if (window != null){
                window.setGravity(Gravity.TOP);
                window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                TranslucentUtils.setTranslucentBoth(window);
                WindowManager.LayoutParams lp = window.getAttributes();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                    lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
                }
                window.setAttributes(lp);
            }
        }
        autoFitBarHeight();
    }

    @Override
    public void dismissAllowingStateLoss() {
        super.dismissAllowingStateLoss();
        requireFragmentManager().beginTransaction().remove(this).commitAllowingStateLoss();
    }

    public void show(){
        try {
            if (act != null){
                fm = act.getSupportFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                if (TextUtils.isEmpty(showTag)){
                    showTag = setShowTag();//取用户设置的tag
                    if (TextUtils.isEmpty(showTag)){
                        showTag = makeShowTag();//自动生成一个唯一tag
                    }
                }
                super.show(transaction,showTag);
            }else {
                Log.e(TAG,"无法启动Dialog，activity为NULL,请调用setDialogActivity()设置activity");
            }
        }catch (Exception e){
            Log.e(TAG,"无法启动Dialog，应该是传递的activity存在问题");
        }
    }

    public void setCanceledOnTouchOutside(boolean b){
        canCancel = b;
        if (dialog != null){
            dialog.setCanceledOnTouchOutside(b);
        }

    }

    public void autoFitBarHeight(){
        if (statusBar != null){
            BarUtils.autoFitStatusBar(getActivity(),statusBar);
        }
        if (navBar != null){
            BarUtils.NavbarHeightCallback(getActivity(), new OnNavHeightListener() {
                @Override
                public void getHeight(int height, int navbarType) {
                    int realHeight = 0;
                    switch (navbarType){
                        case BarUtils.NORMAL_NAVIGATION:
                        case BarUtils.NO_NAVIGATION:
                            realHeight = height;//设置对应高度
                            break;
                        case BarUtils.GESTURE_NAVIGATION:
                            if (isImmerse){
                                realHeight = 0;//手势做沉浸式,高度强制为0
                            }else {
                                realHeight = height;//设置对应高度
                            }
                            break;
                    }
                    ViewGroup.LayoutParams params = navBar.getLayoutParams();
                    params.height = realHeight;
                    navBar.setLayoutParams(params);
                }
            });
        }
    }

    private String makeShowTag(){
        return System.currentTimeMillis()+"";
    }

    public void setDialogActivity(FragmentActivity activity){
        this.act = activity;
    }

    public FragmentActivity getDialogActivity(){
        return act;
    }


    //传递style的id
    abstract public int setStyleId();
    //传递背景图的id
    abstract public int setBackgroundId();

    //传递状态栏id
    abstract public int setStatusBarId();
    //传递导航栏id
    abstract public int setNavigationBarId();

    //设计图UI是否会沉浸显示到小白条（导航栏）下方，仅对小白条生效，不会对三大金刚生效
    abstract public boolean isUIimmerseNavbar();

    //传递dialog的Tag
    //Tag每个独立的dialog必须唯一，如果同一个activity里出现重复的Tag会造成复用问题
    //如果不写会使用时间戳作为tag
    abstract public String setShowTag();
}
