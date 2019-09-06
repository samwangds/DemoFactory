package demo.com.sam.demofactory.activity.livedata;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

import demo.com.sam.demofactory.R;

/**
 * 跨fragment可以共用livedata
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
        final TextView tv = findViewById(R.id.tv);
        tv.setText("A");


        LiveDataHolder.getInstance().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                tv.setText(s);
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ActivityB.class);
                overridePendingTransition(R.anim.anim_rotate_in, R.anim.anim_rotate_out);
            }
        });



    }
}
