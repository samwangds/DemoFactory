package demo.com.sam.demofactory.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by samwang on 2018/4/7.
 */

public class SamIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SamIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String action = intent.getStringExtra("task_action");
        SystemClock.sleep(3000);

        Log.d("Sam", "SamIntentService onHandleIntent "+ action);
    }

    public static void start(Context context, String taskName) {
        Intent intent = new Intent(context, SamIntentService.class);
        intent.putExtra("task_action", taskName);
    }
}
