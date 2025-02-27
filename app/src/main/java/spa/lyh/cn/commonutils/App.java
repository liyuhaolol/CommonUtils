package spa.lyh.cn.commonutils;

import android.app.Application;
import android.content.Context;

import spa.lyh.cn.languagepack.LanguagesPack;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LanguagesPack.init(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        // 国际化适配（绑定语种）
        super.attachBaseContext(LanguagesPack.attach(base));
    }
}
