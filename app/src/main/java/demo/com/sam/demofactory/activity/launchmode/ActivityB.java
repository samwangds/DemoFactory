package demo.com.sam.demofactory.activity.launchmode;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

import demo.com.sam.demofactory.R;
import demo.com.sam.demofactory.activity.ActivityLifeCycleTest;

/**
 * Created by SamWang(199004) on 2018/3/28.
 */
public class ActivityB extends BaseActivity {
    @Override
    protected int getLayoutRes() {
        return R.layout.layout_tv;
    }

    @Override
    protected void initView() {
        super.initView();
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText("B");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, ActivityLifeCycleTest.class);

                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}
