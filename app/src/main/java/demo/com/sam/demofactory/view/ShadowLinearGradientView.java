package demo.com.sam.demofactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试渐变色
 *
 * @author SamWang
 * @date 2019/3/18
 */
public class ShadowLinearGradientView extends View {


    public ShadowLinearGradientView(Context context) {
        super(context);
        init();
    }

    public ShadowLinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShadowLinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);



        Shader shader = new LinearGradient(w, 0, 0, h,
                Color.BLACK, Color.WHITE, Shader.TileMode.REPEAT);
//        Shader shader = new LinearGradient(w*3, 0, 0, h*3,
//                Color.parseColor("#f6f3ff"), Color.parseColor("#bf9ff3"), Shader.TileMode.REPEAT);

        paint.setShader(shader);
        paint.setColor(Color.WHITE);

    }

    void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);

//        setLayerType(LAYER_TYPE_SOFTWARE, null);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);

    }
}
