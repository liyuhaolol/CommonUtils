package spa.lyh.cn.lib_utils.dialog;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public abstract class FullDialog extends DialogFragment {
    public final static String TAG = "FullDialog";
    private FragmentActivity act = null;
    private FragmentManager fm = null;

    public String showTag = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int styleId = setStyleId();
        if (styleId != 0){
            setStyle(STYLE_NO_TITLE,styleId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
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

    //传递dialog的Tag
    //Tag每个独立的dialog必须唯一，如果同一个activity里出现重复的Tag会造成复用问题
    //如果不写会使用时间戳作为tag
    abstract public String setShowTag();
}
