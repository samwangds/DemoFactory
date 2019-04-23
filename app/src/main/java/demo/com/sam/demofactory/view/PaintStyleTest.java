package demo.com.sam.demofactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 测试PaintStyle
 *
 * FILL
 * STROKE
 * FILL_AND_STROKE
 *
 * 测试 stroke width 影响; stroke 一半在图形内，一半在图形外
 *
 * https://hencoder.com/ui-1-2/
 *
 * @author SamWang
 * @date 2019/3/18
 */
public class PaintStyleTest extends View {

    public PaintStyleTest(Context context) {
        super(context);
        init();
    }

    public PaintStyleTest(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PaintStyleTest(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint paint;
    private Paint paintWithStroke;
    private Paint paintFillAndStroke;
    private Paint paintFillAndStrokeWidth;
    private Paint paintLine;

    void init() {

        paint = new Paint();
        paint.setColor(Color.BLACK);

        paintWithStroke = new Paint(paint);
        paintWithStroke.setStyle(Paint.Style.STROKE);
        paintWithStroke.setStrokeWidth(20);//描边一半在内一半在外
//        paintWithStroke.setStrokeJoin(Paint.Join.ROUND);//外角的形状

        paintFillAndStroke = new Paint(paint);
        paintFillAndStroke.setStyle(Paint.Style.FILL_AND_STROKE);

        paintFillAndStrokeWidth = new Paint(paintFillAndStroke);
        paintFillAndStrokeWidth.setStrokeWidth(20);


        paintLine = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintLine.setColor(Color.RED);
        paintLine.setStrokeWidth(10);
//        paintLine.setStrokeCap(Paint.Cap.ROUND); //线头是圆

    }

    float cy;
    float cx;
    final float a = 200;
    final float b = 100;
    float offset = 20;
    float startX;
    float endX;
    float offsetY;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cx = w / 2;
        cy = h / 2;
        startX = cx - a;
        endX = cx + a;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        offsetY = 0;
        drawRect(canvas, paint);
        drawRect(canvas, paintWithStroke);
        drawRect(canvas, paintFillAndStroke);
        drawRect(canvas, paintFillAndStrokeWidth);





    }


    private void drawRect(Canvas canvas, Paint paint) {
        canvas.drawRect(startX, offsetY, endX, offsetY + b, paint);
        canvas.drawLine(startX, offsetY, endX, offsetY, paintLine);
        offsetY += (b + offset);

    }
}
