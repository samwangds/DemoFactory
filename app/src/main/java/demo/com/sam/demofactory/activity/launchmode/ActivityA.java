package demo.com.sam.demofactory.activity.launchmode;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;
import com.sam.lib.utils.ScreenUtils;

import demo.com.sam.demofactory.R;

/**
 * Created by SamWang(199004) on 2018/3/28.
 */
public class ActivityA extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_tv;
    }

    @Override
    protected void initView() {
        super.initView();
        TextView tv = (TextView) findViewById(R.id.tv);


        tv.setText("A");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ActivityB.class);
                overridePendingTransition(R.anim.anim_rotate_in, R.anim.anim_rotate_out);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("Sam", "ActivityLifeCycleTest onCreate " + this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Sam", "ActivityLifeCycleTest onStart " + this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Sam", "ActivityLifeCycleTest onResume " + this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Sam", "ActivityLifeCycleTest onPause " + this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Sam", "ActivityLifeCycleTest onRestart " + this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Sam", "ActivityLifeCycleTest onStop " + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Sam", "ActivityLifeCycleTest onDestroy "+ this);
    }

    @Override
    public void recreate() {
        super.recreate();
        Log.i("Sam", "ActivityLifeCycleTest recreate "+this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i("Sam", "ActivityLifeCycleTest onNewIntent ");
    }
}
