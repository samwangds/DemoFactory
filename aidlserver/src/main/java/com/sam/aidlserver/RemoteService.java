package com.sam.aidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.List;

/**
 * Created by samwang on 2017/11/11.
 */

public class RemoteService extends Service{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Sam", "RemoteService onCreate ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("Sam", "RemoteService onStartCommand ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i("Sam", "RemoteService onBind ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("Sam", "RemoteService onUnbind  false");
        return false;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i("Sam", "RemoteService onRebind");

    }

    AidlRemote.Stub iBinder = new AidlRemote.Stub() {
        @Override
        public int getCount(List<TestModel> models) throws RemoteException {
            int result = 0;
            if (models != null) {
                for (TestModel model : models) {
                    result += model.getNum();
                }
            }
            return result;
        }

    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("Sam", "RemoteService onDestroy");

    }
}
