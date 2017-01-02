package demo.com.sam.demofactory;

import android.app.Application;
import android.content.Intent;
import android.test.ApplicationTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ServiceLifeTest extends ApplicationTestCase<Application> {
    public ServiceLifeTest() {
        super(Application.class);
    }

    public void testService() {
        Log.d("Sam", "SyncService startService ");
        Intent intent = new Intent(getContext(), LifecycleTestService.class);
        getContext().startService(intent);
        try {
            Thread.sleep(6500);
        } catch (InterruptedException e) {
            Log.e("Sam", "ServiceLifeTest testName ", e);
        }

        getContext().stopService(intent);
        Log.e("Sam", "ServiceLifeTest testService stop! ");
        getContext().startService(intent);

        try {
            Thread.sleep(6500);
        } catch (InterruptedException e) {
            Log.e("Sam", "ServiceLifeTest testName ", e);
        }

    }

    public void testNullInstanceof() throws Exception {
        assertTrue(null instanceof Calendar);
    }

    public void testStringEq() {
        String test1 = "abcde";
        String test2 = "abcde2";
        assertFalse(test1.equals(test2));
        List<String> list = new ArrayList<>();
        list.add(test1);
        StringBuilder stringBuilder = new StringBuilder(test2);
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        assertTrue(list.contains(stringBuilder.toString()));

    }
}