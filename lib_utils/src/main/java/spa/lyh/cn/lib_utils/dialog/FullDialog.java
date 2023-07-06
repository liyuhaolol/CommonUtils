package spa.lyh.cn.lib_utils.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import spa.lyh.cn.lib_utils.translucent.TranslucentUtils;
import spa.lyh.cn.lib_utils.translucent.navbar.NavBarFontColorControler;

public abstract class FullDialog extends DialogFragment {
    public final static String TAG = "FullDialog";
    private FragmentActivity act = null;
    private FragmentManager fm = null;

    public String showTag = null;

    public Window window;

    private boolean canCancel = true;

    private View background;

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
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        Dialog dialog = getDialog();
        if (dialog != null){
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

    //传递dialog的Tag
    //Tag每个独立的dialog必须唯一，如果同一个activity里出现重复的Tag会造成复用问题
    //如果不写会使用时间戳作为tag
    abstract public String setShowTag();
}
