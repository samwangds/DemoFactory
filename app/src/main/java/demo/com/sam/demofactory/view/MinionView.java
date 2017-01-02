package demo.com.sam.demofactory.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author SamWang(199004)
 *         2016/1/18 10:30
 */
public class MinionView extends View {
    public MinionView(Context context) {
        super(context);
    }

    public MinionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MinionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MinionView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        initParams();
        initPaint();
        drawFeet(canvas);//脚
        drawBody(canvas);//身体
        drawClothes(canvas);//衣服
        drawEyesMouth(canvas);//眼睛,嘴巴
    }


    private Paint mPaint;
    private float bodyWidth;
    private float bodyHeigh;
    private static final float BODY_SCALE = 0.6f;//身体主干占整个view的比重
    private float mStrokeWidth = 4;//描边宽度
    private float offset ;//计算时，部分需要 考虑找边偏移
    private float radius ;//身体上下半圆的半径
    private int colorClothes = Color.rgb(32, 116, 160);//衣服的颜色
    private int colorBody = Color.rgb(249, 217, 70);//衣服的颜色

    private RectF bodyRect;
    private void initParams(){
//        身体的比例设定为 w:h = 3:5
        bodyWidth = (float) (Math.min(getWidth()/3,getHeight()/5) * 3 * 0.6);
        bodyHeigh =  (float) (Math.min(getWidth()/3,getHeight()/5) * 5 * 0.6);

        mStrokeWidth = Math.max(bodyWidth / 50, mStrokeWidth);
        offset = mStrokeWidth /2;

        bodyRect = new RectF();
        bodyRect.left = (getWidth() - bodyWidth )/2;
        bodyRect.top = (getHeight() - bodyHeigh)/2;
        bodyRect.right = bodyRect.left + bodyWidth;
        bodyRect.bottom = bodyRect.top +bodyHeigh;

    }

    private void drawBody(Canvas canvas){

        initPaint();
        mPaint.setColor(colorBody);
        mPaint.setStyle(Paint.Style.FILL);

        radius = bodyWidth / 2;
        canvas.drawRoundRect(bodyRect, radius, radius, mPaint);
        initPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRoundRect(bodyRect, radius, radius, mPaint);
    }

    private void drawClothes(Canvas canvas){
        initPaint();

        RectF rect = new RectF();

        rect.left = (getWidth() - bodyWidth )/2 +offset;
        rect.top = (getHeight() + bodyHeigh)/2  - radius * 2+offset;
        rect.right = rect.left + bodyWidth-offset*2;
        rect.bottom = rect.top + radius*2 -offset*2;

        mPaint.setColor(colorClothes);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        canvas.drawArc(rect, 0, 180, true, mPaint);

        int h = (int) (radius * 0.5);
        int w = (int) (radius * 0.3);

        rect.left += w;
        rect.top =rect.top + radius - h;
        rect.right -= w;
        rect.bottom = rect.top+h;

        canvas.drawRect(rect, mPaint);

        //画横线
        initPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(mStrokeWidth);
        float[] pts = new float[20];//5条线

        pts[0] = rect.left -w;
        pts[1] = rect.top +h;
        pts[2] = pts[0] +w;
        pts[3] = pts[1];

        pts[4] = pts[2];
        pts[5] = pts[3] + offset;
        pts[6] = pts[4];
        pts[7] = pts[3] - h;

        pts[8] = pts[6]- offset;
        pts[9] = pts[7];
        pts[10] = pts[8]+(radius - w)*2;
        pts[11] = pts[9];

        pts[12] = pts[10];
        pts[13] = pts[11]- offset;
        pts[14] = pts[12];
        pts[15] = pts[13] + h;

        pts[16] = pts[14]- offset;
        pts[17] = pts[15];
        pts[18] = pts[16]+w;
        pts[19] = pts[17] ;
        canvas.drawLines(pts, mPaint);

        //画左吊带
        initPaint();
//        mPaint.setColor(Color.RED);
        mPaint.setColor(colorClothes);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        Path path = new Path();
//        canvas.drawPoint( rect.left - w, rect.top -  w/2,mPaint);

        path.moveTo(rect.left - w, rect.top - w / 2);
        path.lineTo(rect.left + h / 4, rect.top + h / 2);
        final float smallW = w / 2 * (float) Math.sin(Math.PI / 4);
        path.lineTo(rect.left + h / 4 + smallW, rect.top + h / 2 - smallW);
        final float smallW2 = w /  (float) Math.sin(Math.PI / 4)/2;
        path.lineTo(rect.left - w, rect.top - w / 2 - smallW2);

        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rect.left + h / 4, rect.top + h / 4, mStrokeWidth, mPaint);

        //画右吊带

        initPaint();
        mPaint.setColor(colorClothes);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);
        path.reset();
        path.moveTo(rect.left - w + 2 * radius - offset, rect.top - w / 2);
        path.lineTo(rect.right - h / 4, rect.top + h / 2);
        path.lineTo(rect.right - h / 4 - smallW, rect.top + h / 2 - smallW);
        path.lineTo(rect.left - w + 2 * radius - offset, rect.top - w / 2 - smallW2);

        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawPath(path, mPaint);
        initPaint();
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(rect.right - h / 4, rect.top + h / 4, mStrokeWidth, mPaint);

        //中间口袋
        initPaint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);

        path.reset();
        float radiusBigPokect = w / 2.0f;
        path.moveTo(rect.left + 1.5f * w, rect.bottom - h / 4);
        path.lineTo(rect.right - 1.5f * w, rect.bottom - h / 4);
        path.lineTo(rect.right - 1.5f * w, rect.bottom + h / 4);
        path.addArc(rect.right - 1.5f * w - radiusBigPokect * 2, rect.bottom + h / 4 - radiusBigPokect,
                rect.right - 1.5f * w, rect.bottom + h / 4 + radiusBigPokect, 0, 90);
        path.lineTo(rect.left + 1.5f * w + radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect);

        path.addArc(rect.left + 1.5f * w, rect.bottom + h / 4 - radiusBigPokect,
                rect.left + 1.5f * w + 2 * radiusBigPokect, rect.bottom + h / 4 + radiusBigPokect, 90, 90);
        path.lineTo(rect.left + 1.5f * w, rect.bottom - h / 4 - offset);
        canvas.drawPath(path, mPaint);

//        下边一竖，分开裤子
        canvas.drawLine(bodyRect.left+bodyWidth/2,bodyRect.bottom - h * 0.8f,bodyRect.left+bodyWidth/2,bodyRect.bottom,mPaint);
//      左边的小口袋
        float radiusSamllPokect = w *1.2f;
        canvas.drawArc(bodyRect.left -radiusSamllPokect, bodyRect.bottom -radius-radiusSamllPokect,
                bodyRect.left +radiusSamllPokect, bodyRect.bottom -radius + radiusSamllPokect,80,-60,false,mPaint);
//      右边小口袋
        canvas.drawArc(bodyRect.right -radiusSamllPokect, bodyRect.bottom -radius-radiusSamllPokect,
                bodyRect.right +radiusSamllPokect, bodyRect.bottom -radius + radiusSamllPokect,100,60,false,mPaint);
//        canvas.drawArc(left + w/5,);
    }

    private void drawEyesMouth(Canvas canvas) {
        mPaint.setStrokeWidth(mStrokeWidth *5);
//        计算眼镜带弧行的半径 分两段，以便眼睛中间有隔开的效果
        float radiusGlassesRibbon = (float) (radius /Math.sin(Math.PI/20));
        RectF rect = new RectF();
        rect.left = bodyRect.left + radius - radiusGlassesRibbon;
        rect.top =  bodyRect.top +radius -(float) (radius/Math.tan(Math.PI/20)) - radiusGlassesRibbon;
        rect.right =    rect.left + radiusGlassesRibbon * 2 ;
        rect.bottom =  rect.top + radiusGlassesRibbon * 2 ;
        canvas.drawArc(rect, 81,3,false,mPaint);
        canvas.drawArc(rect, 99,-3,false,mPaint);


//眼睛半径
        float radiusEyes = radius  /3;
        initPaint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL);

        canvas.drawCircle(bodyRect.left + bodyWidth / 2 - radiusEyes - offset, bodyRect.top + radius, radiusEyes, mPaint);
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 + radiusEyes + offset, bodyRect.top + radius, radiusEyes, mPaint);

        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 - radiusEyes - offset, bodyRect.top + radius, radiusEyes, mPaint);
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 + radiusEyes + offset, bodyRect.top + radius, radiusEyes, mPaint);

        final float radiusEyeballBlack = radiusEyes / 3;
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 - radiusEyes - offset, bodyRect.top + radius, radiusEyeballBlack, mPaint);
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 + radiusEyes + offset, bodyRect.top + radius, radiusEyeballBlack, mPaint);

        mPaint.setColor(Color.WHITE);
        final float radiusEyeballWhite = radiusEyeballBlack / 2;
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 - radiusEyes + radiusEyeballWhite - offset * 2,
                bodyRect.top + radius - radiusEyeballWhite + offset, radiusEyeballWhite, mPaint);
        canvas.drawCircle(bodyRect.left + bodyWidth / 2 + radiusEyes + radiusEyeballWhite - offset, bodyRect.top + radius - radiusEyeballWhite + offset,
                radiusEyeballWhite, mPaint);



//        画嘴巴，因为位置和眼睛有相对关系，所以写在一块
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mStrokeWidth );
        float radiusMonth =  radius ;
        rect.left = bodyRect.left  ;
        rect.top =  bodyRect.top - radiusMonth/2.5f;
        rect.right =    rect.left + radiusMonth * 2 ;
        rect.bottom =  rect.top + radiusMonth * 2 ;
        canvas.drawArc(rect, 95, -20, false, mPaint);

//        嘴角弧形的半径

    }


    private void drawFeet(Canvas canvas) {

        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        //      左脚
        Path path = new Path();
        float radiusFoot = radius /3 *0.4f;
        float leftFootStartX = bodyRect.left + radius - offset * 2;
        float leftFootStartY = bodyRect.bottom - offset;
        float footHeigh = radius * 1.3f /3;
        float footWidthA = radius /3 *1.5f;//脚宽度大-到半圆结束
        float footWidthB = radius /3 *0.5f;//脚宽度-比较细的部分

        path.moveTo(leftFootStartX, leftFootStartY);
        path.lineTo(leftFootStartX, leftFootStartY + footHeigh);
        path.lineTo(leftFootStartX  - footWidthA + radiusFoot, leftFootStartY + footHeigh);

        RectF rectF = new RectF();
        rectF.left =leftFootStartX  - footWidthA ;
        rectF.top =   leftFootStartY + footHeigh- radiusFoot * 2;
        rectF.right =  rectF.left +radiusFoot *2;
        rectF.bottom =  rectF.top + radiusFoot * 2;
        path.addArc(rectF,90,180);
        path.lineTo(rectF.left + radiusFoot + footWidthB, rectF.top);
        path.lineTo(rectF.left + radiusFoot + footWidthB, leftFootStartY);
        path.lineTo(leftFootStartX, leftFootStartY);
        canvas.drawPath(path,mPaint);

//      右脚
        float rightFootStartX = bodyRect.left + radius + offset * 2;
        float rightFootStartY = leftFootStartY;
        path.reset();
        path.moveTo(rightFootStartX, rightFootStartY);
        path.lineTo(rightFootStartX, rightFootStartY + footHeigh);
        path.lineTo(rightFootStartX + footWidthA - radiusFoot, rightFootStartY + footHeigh);

        rectF.left = rightFootStartX  + footWidthA -radiusFoot * 2;
        rectF.top = rightFootStartY + footHeigh- radiusFoot * 2;
        rectF.right =  rectF.left + radiusFoot *2;
        rectF.bottom =  rectF.top + radiusFoot * 2;
        path.addArc(rectF,90,-180);
        path.lineTo(rectF.right - radiusFoot - footWidthB, rectF.top);
        path.lineTo(rectF.right - radiusFoot - footWidthB, rightFootStartY);
        path.lineTo(rightFootStartX, rightFootStartY);


        canvas.drawPath(path,mPaint);

    }

    private void initPaint(){
        if(mPaint == null){
            mPaint = new Paint();
        }else{
            mPaint.reset();
        }
        mPaint.setAntiAlias(true);//边缘无锯齿
    }
}
