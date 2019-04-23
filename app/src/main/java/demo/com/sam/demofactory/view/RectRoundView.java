package demo.com.sam.demofactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * 绘制圆角
 * @author SamWang
 * @date 2019/3/20
 */
public class RectRoundView extends View {

    private float density;

    public RectRoundView(Context context) {
        this(context,null);
    }

    public RectRoundView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RectRoundView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }

    /**
     * 带描边的路径
     */
    private Path path;
    private RectF rect;
    /**
     * 上下左右 是否有圆角
     */
    private boolean topLeftCorner = true;
    private boolean topRightCorner = true;
    private boolean bottomRightCorner= true;
    private boolean bottomLeftCorner= true;

    private Paint paint;
    private float r;

    private void init() {

        density = getResources().getDisplayMetrics().density;

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(0.5f * density);
        r = 2 * density;
        rect = new RectF();
        path = new Path();
    }

    public Paint getPaint() {
        return paint;
    }

    public void setFillStyle() {
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(0);
    }

    public void setStrokeWidth(int dpWidth) {
        paint.setStrokeWidth(dpWidth * density);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

//        int cx = w / 2;
//        int cy = h / 2;
//
//        rect.left = cx - w/4;
//        rect.right = cx + w/4;
//        rect.top = cy - h/4;
//        rect.bottom = cy + h/4;

        rect.left = 0;
        rect.right = w;
        rect.top = 0;
        rect.bottom = h;

        path.reset();
        float d = r * 2;
        if (topLeftCorner) {
            path.moveTo(rect.left, rect.top + r);
            path.arcTo(new RectF(rect.left ,rect.top,rect.left + d,rect.top + d) ,180,90);
        } else {
            path.moveTo(rect.left, rect.top);
        }

        if (topRightCorner) {
            path.lineTo(rect.right - r, rect.top);
            path.arcTo(new RectF(rect.right - d ,rect.top,rect.right,rect.top + d) ,270,90);
        } else {
            path.lineTo(rect.right, rect.top);
        }

        if (bottomRightCorner) {
            path.lineTo(rect.right, rect.bottom-r);
            path.arcTo(new RectF(rect.right - d ,rect.bottom-d,rect.right,rect.bottom ) ,0,90);

        } else {
            path.lineTo(rect.right, rect.bottom);
        }

        if (bottomLeftCorner) {
            path.lineTo(rect.left + r, rect.bottom);
            path.arcTo(new RectF(rect.left, rect.bottom - d, rect.left + d, rect.bottom), 90, 90);

        } else {
            path.lineTo(rect.left, rect.bottom);
        }

        path.close();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawPath(path, paint);
//        canvas.drawRect(rect, paint);
        canvas.drawRoundRect(rect,r,r,paint);
    }
}
