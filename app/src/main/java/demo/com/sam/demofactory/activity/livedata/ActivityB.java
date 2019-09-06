package demo.com.sam.demofactory.activity.livedata;

import android.arch.lifecycle.Observer;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

import demo.com.sam.demofactory.R;

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
        final TextView tv = findViewById(R.id.tv);
        tv.setBackgroundColor(Color.RED);
        final LiveDataHolder liveDataHolder = LiveDataHolder.getInstance();
        liveDataHolder.observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tv.setText(s);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                liveDataHolder.countAdd();
            }
        });
    }
}
