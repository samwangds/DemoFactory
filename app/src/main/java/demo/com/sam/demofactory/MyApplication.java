package demo.com.sam.demofactory;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by samwang on 2018/4/27.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);
    }
}
