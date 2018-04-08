package demo.com.sam.demofactory.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import demo.com.sam.demofactory.R;
import demo.com.sam.demofactory.activity.launchmode.ActivityA;

public class ActivityLifeCycleTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Sam", "ActivityLifeCycleTest onCreate " + this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent = new Intent(ActivityLifeCycleTest.this, ActivityA.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });


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
