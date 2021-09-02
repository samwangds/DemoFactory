package demo.com.sam.demofactory.activity;

import android.view.View;
import android.widget.TextView;

import com.sam.lib.app.BaseActivity;

import demo.com.sam.demofactory.R;
import demo.com.sam.demofactory.fragment.TestDialogFragment;

/**
 * 结论：DialogFragment dismiss 之后会 remove fragment ,走 fragment onDestroy
 * 如果 Activity 还持有 这个 Dialog 的引用，则内存泄漏
 *
 * Created by SamWang
 */
public class TestDialogFragmentActivity extends TextActivity {
    @Override
    protected void setText(TextView tv) {
        tv.setText("Show");
    }

    private TestDialogFragment df = new TestDialogFragment();
    @Override
    public void onClick(View v) {
        df.show(getSupportFragmentManager(), "TestDialogFragment");
        v.postDelayed(
                new Runnable() {
                    @Override
                    public void run() {
                        df.dismiss();
                    }
                }
                , 2000);
    }
}
