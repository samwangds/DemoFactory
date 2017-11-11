package com.sam.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sam.aidlserver.AidlRemote;
import com.sam.aidlserver.TestModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv);
        textView.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService();
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService();
    }

    private void unbindService() {
        unbindService(connection);
    }

    private void bindService() {
        // 获取到服务端
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.sam.aidlserver", "com.sam.aidlserver.RemoteService"));
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    @Override
    public void onClick(View v) {
        List<TestModel> list = new ArrayList<>();
        list.add(new TestModel("hello ", 1));
        list.add(new TestModel("world !", 1));
        try {
            int result = aidlRemote.getCount(list);
            Toast.makeText(MainActivity.this, "aidl result "+ result, Toast.LENGTH_SHORT).show();
        } catch (RemoteException e) {
            Log.e("Sam", "MainActivity onClick ",e);
        }
    }

    AidlRemote aidlRemote;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            aidlRemote = AidlRemote.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            aidlRemote = null;
        }
    };
}
