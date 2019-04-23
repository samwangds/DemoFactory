package demo.com.sam.demofactory.activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.security.MessageDigest;

import demo.com.sam.demofactory.R;
import demo.com.sam.demofactory.view.PaintStyleTest;
import demo.com.sam.demofactory.view.RectRoundView;
import demo.com.sam.demofactory.view.ScaleView;
import demo.com.sam.demofactory.view.ShadowLinearGradientView;
import demo.com.sam.demofactory.view.ShadowView;


public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        testRoundCorners();

//        setContentView(test());


//        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        String id = androidID + Build.SERIAL;
//        Log.e(" sam ", "onCreate: " + androidID + "," + id);

    }

    /**
     * 测试两种圆角绘制的差异
     */
    private void testRoundCorners() {
        setContentView(R.layout.activity_round);
        RectRoundView view = (RectRoundView) findViewById(R.id.rectRoundView);
        view.setFillStyle();

//        RectRoundView view2 = (RectRoundView) findViewById(R.id.rectRoundView2);
//        view2.getPaint().setColor(Color.RED);
//        view2.setStrokeWidth(2);
    }


    private View test() {
        View view = new PaintStyleTest(this);
        setLayout(view);
        return view;


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

    private View testRectRoundView() {
        RectRoundView view = new RectRoundView(this);
        setLayout(view);
        return view;
    }

    private void setLayout(View view) {
        view.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
    }

}
