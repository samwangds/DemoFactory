package demo.com.sam.demofactory.activity.launchmode;

import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

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
            }
        });

    }
}
