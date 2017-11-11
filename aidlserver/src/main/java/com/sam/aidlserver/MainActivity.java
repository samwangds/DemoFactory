package com.sam.aidlserver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, RemoteService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       stopService(new Intent(this, RemoteService.class));
    }
}
