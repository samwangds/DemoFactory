package demo.com.sam.demofactory.activity;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import demo.com.sam.demofactory.R;
import demo.com.sam.demofactory.util.Utils;
import demo.com.sam.demofactory.view.CubicBezierView;
import demo.com.sam.demofactory.view.PaintStyleTest;
import demo.com.sam.demofactory.view.RoundRectMask;
import demo.com.sam.demofactory.view.RectRoundView;
import demo.com.sam.demofactory.view.ScaleView;
import demo.com.sam.demofactory.view.ShadowView;
import demo.com.sam.demofactory.view.StrokeCircleView;
import demo.com.sam.demofactory.view.SwordLoadingView;


public class CustomViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        testRoundCorners();

        setContentView(swordLoadingView());


//        String androidID = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
//        String id = androidID + Build.SERIAL;
//        Log.e(" sam ", "onCreate: " + androidID + "," + id);

    }

    private View swordLoadingView() {
        SwordLoadingView view = new SwordLoadingView(this);
        FrameLayout.LayoutParams fl = new FrameLayout.LayoutParams(Utils.getDp(200), Utils.getDp(200));
        fl.gravity = Gravity.CENTER;
        view.setLayoutParams(fl);
        view.getAnim().start();
        return view;
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
        RoundRectMask view = new RoundRectMask(this);
        view.setCornerRadiusDp(100);
        view.setCorners(true,true,true,true);
        setLayout(view);
        return view;
    }
    private View testShadow() {
        ShadowView shadowView = new ShadowView(this);
        setLayout(shadowView);
        return shadowView;
    }

    private View testCircleStore() {
        View view = new StrokeCircleView(this);
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return view;
    }
    private View testScaleView() {
        ScaleView view = new ScaleView(this);
        setLayout(view);
        return view;
    }

    private View testRectRoundView() {
        RectRoundView view = new RectRoundView(this);
        setLayout(view);
        return view;
    } private View testPaintStyleTest() {
        View view = new PaintStyleTest(this);
        setLayout(view);
        return view;
    }

    private View testCurveView() {
        CubicBezierView view = new CubicBezierView(this);
        view.getPoints().add(new PointF(0f, 0.1f));
        view.getPoints().add(new PointF(0.25f, 0.25f));
        view.getPoints().add(new PointF(0.5f, 0.5f));
        view.getPoints().add(new PointF(1f, 1f));
        view.setBackgroundColor(Color.BLACK);
        view.setPointEvent(new CubicBezierView.Event() {
            @Override
            public void onEvent(float scaleX, float scaleY, int pointIndex) {
                Log.i("Sam", scaleX + " , " + scaleY + ", " + pointIndex);
            }
        });
        setLayout(view);
        return view;
    }

    private void setLayout(View view) {
        view.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getDp(200)));
    }

}
