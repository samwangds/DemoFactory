package demo.com.sam.demofactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试阴影
 *
 * @author SamWang
 * @date 2019/3/18
 */
public class ShadowView extends View {

    public ShadowView(Context context) {
        super(context);
        init();
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;
    private Paint paintShadow;

    void init() {
        setBackgroundColor(Color.WHITE);

        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(1);

        paintShadow = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintShadow.setColor(Color.BLACK);
        paintShadow.setShadowLayer(20, 0, 10, Color.RED);

        setLayerType(LAYER_TYPE_SOFTWARE, null);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float w = getWidth();
        float h = getHeight();
        float r = Math.min(w, h) / 3;

        canvas.drawCircle(w / 2, h / 2, r, paintShadow);

        canvas.drawCircle(w / 2, h / 2, r + 20, paint);
        canvas.drawCircle(w / 2, h / 2, r + 30, paint);
        canvas.drawCircle(w / 2, h / 2, r + 50, paint);


    }
}
