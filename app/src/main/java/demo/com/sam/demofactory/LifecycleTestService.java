package demo.com.sam.demofactory;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * 用来验证service停止后马上开启会不会
 *
 * @author SamWang(199004)
 *         2016/11/18 14:22
 */
public class LifecycleTestService extends Service {

    private TestThread mTestThread;
    private final IBinder mBinder = new MyBinder();

    public class MyBinder extends Binder{
        LifecycleTestService getServices(){
            return LifecycleTestService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Sam", "LifecycleTestService onCreate " + this);
        init();
    }
    @Override
    public void onDestroy() {
        Log.e("Sam", "LifecycleTestService onDestroy " + this);
        mTestThread.isRuning = false;
        mTestThread.interrupt();
        super.onDestroy();

    }

    void init() {
        Log.e("Sam", "LifecycleTestService init " + this);
        mTestThread = new TestThread();
        mTestThread.isRuning = true;
        mTestThread.start();

    }



    private class TestThread extends Thread {
        boolean isRuning;
        @Override
        public void run() {
            while (isRuning) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    Log.e("Sam", "TestThread run " + e);
                }

                Log.e("Sam", " TestThread run " + this);

            }
        }
    }
}
