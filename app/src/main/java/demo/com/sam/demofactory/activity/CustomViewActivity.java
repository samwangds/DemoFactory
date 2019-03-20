package demo.com.sam.demofactory.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import demo.com.sam.demofactory.view.ScaleView;
import demo.com.sam.demofactory.view.ShadowView;

public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(testSacleView());

    }

    private View testShadow() {
        ShadowView shadowView = new ShadowView(this);
        setLayout(shadowView);
        return shadowView;
    }

    private View testSacleView() {
        ScaleView view = new ScaleView(this);
        setLayout(view);
        return view;
    }

    private void setLayout(View view) {
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

}
