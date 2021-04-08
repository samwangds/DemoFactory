package demo.com.sam.demofactory;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import demo.com.sam.demofactory.activity.ActivityLifeCycleTest;
import demo.com.sam.demofactory.activity.CustomViewActivity;
import demo.com.sam.demofactory.activity.launchmode.ActivityA;
import demo.com.sam.demofactory.activity.launchmode.RecyclerViewTest;
import demo.com.sam.demofactory.service.NoZuoNoDieService;
import demo.com.sam.demofactory.view.CustomView;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().build());
//        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build());

        long l = System.currentTimeMillis();
        setContentView(R.layout.activity_main);
        Log.e("Sam", "MainActivity onCreate strictmode " +(System.currentTimeMillis() - l));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        recyclerView.setAdapter(new MyAdapter());

        startService(new Intent(this, NoZuoNoDieService.class));


    }

    /**
     * 获取手机IMEI号
     *
     * 需要动态权限: android.permission.READ_PHONE_STATE
     */
    public static String getIMEI(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);
        String imei = telephonyManager.getDeviceId();

        return imei;
    }

    /**
     * 可测试service的生命周期
     */
    private void testLifecycleTestService() {
        final ServiceConnection serviceConnection = new MyServiceConnection();
        bindService(new Intent(this, LifecycleTestService.class), serviceConnection,BIND_AUTO_CREATE);
        startService(new Intent(this, LifecycleTestService.class));
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(MainActivity.this, LifecycleTestService.class));
//                unbindService(serviceConnection);
            }
        });
    }
    static class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e("Sam", "MainActivity onServiceConnected ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.e("Sam", "MainActivity onServiceDisconnected ");
        }
    }



    class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        List<Pair<String, Class>> data;

        MyAdapter() {
            data = new ArrayList<>();
//            data.add(new Pair<String, Class>("录屏测试", ScreenRecorderActivity.class));
            data.add(new Pair<String, Class>("Activity生命周期", ActivityLifeCycleTest.class));
            data.add(new Pair<String, Class>("LaunchModelSingleTask", ActivityA.class));
            data.add(new Pair<String, Class>("RecyclerView 测试 ", RecyclerViewTest.class));
            data.add(new Pair<String, Class>("自定义view ", CustomViewActivity.class));
            data.add(new Pair<String, Class>("liveData ", demo.com.sam.demofactory.activity.livedata.ActivityA.class));
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item, null));
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setData(data.get(position));
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;
        Pair<String, Class> data;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
            textView.setOnClickListener(this);
        }

        public void setData(Pair<String, Class> data) {
            this.data = data;
            textView.setText(data.first);
        }

        @Override
        public void onClick(View v) {
            final Intent intent = new Intent(MainActivity.this, data.second);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

}

