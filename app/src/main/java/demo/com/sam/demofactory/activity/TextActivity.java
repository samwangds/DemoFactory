package demo.com.sam.demofactory.activity;

import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

import demo.com.sam.demofactory.R;

/**
 * Created by SamWang(199004) on 2018/4/8.
 */
public abstract class TextActivity  extends BaseActivity implements View.OnClickListener {

    protected TextView tv;

    @Override
    protected int getLayoutRes() {
        return R.layout.layout_tv;
    }

    @Override
    protected void initView() {
        super.initView();
        tv = (TextView) findViewById(R.id.tv);
        tv.setOnClickListener( this );
        setText(tv);

        }

    protected abstract void setText(TextView tv);
}
