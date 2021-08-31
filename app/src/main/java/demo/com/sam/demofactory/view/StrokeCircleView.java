package demo.com.sam.demofactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Printer;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * 测试 STROKE 内存泄漏
 * <p>
 * https://stackoverflow.com/questions/64334771/android-canvas-draw-leaks-graphic-memory
 * when you use a paint object with style STROKE and using canvas.drawCircle(x, y, radius, paint)
 * where the x and y are changed at random - the graphics memory will be increased
 * and never released until an inevitable OOM.
 *
 * @author SamWang
 * @date 2019/3/18
 */
public class StrokeCircleView extends View implements Runnable {


    public StrokeCircleView(Context context) {
        super(context);
        init();
    }

    public StrokeCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public StrokeCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;
    private float r = 0;
    private float x = 0;
    private float y = 0;

    void init() {
        setBackgroundColor(Color.WHITE);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

//        setLayerType(LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        invalidate();
        return true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        r = Math.min(w, h) / 3;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        run();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(x, y, r + 20, paint);
        canvas.drawCircle(x, y, r + 30, paint);
        canvas.drawCircle(x, y, r + 50, paint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stop = true;
        Log.e("Sam", " stop ---- ");
    }

    boolean stop = false;

    Random random = new Random();
    @Override
    public void run() {
        if (stop) {
            return;
        }
        x = random.nextInt(getWidth() + 1);
        y = random.nextInt(getHeight() + 1);
        invalidate();
        postDelayed(this, 50);
    }
}
