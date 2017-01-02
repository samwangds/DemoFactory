package demo.com.sam.demofactory.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;

/**
 * Android画个时钟玩玩
 * @author SamWang(199004)
 * @date 2016/1/11	14:07
 * http://www.jianshu.com/p/ebb726f8fc0b
 */
public class ClockView extends View{

    //View默认最小宽度
    private static final int DEFAULT_MAX_WIDTH_AT_MOST = 200;
    //秒针长度
    private float secondPointerLength;
    //分针长度
    private float minutePointerLength;
    //时针长度
    private float hourPointerLength;
    //外圆边框宽度
    private static final float DEFAULT_BORDER_WIDTH = 6f;
    //指针反向超过圆点的长度
    private static final float DEFAULT_POINT_BACK_LENGTH = 40f;
    //长刻度线
    private static final float DEFAULT_LONG_DEGREE_LENGTH = 40f;
    //短刻度线
    private static final float DEFAULT_SHORT_DEGREE_LENGTH = 30f;
    //数字的size
    public static final int DEGRESS_NUMBER_SIZE = 30;
    private Paint mPaintDegreeNumber;

    public ClockView(Context context) {
        super(context);
        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ClockView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private Thread timeThread = new Thread() {
        @Override
        public void run() {
            try {
                while(true){
                    updateHandler.sendEmptyMessage(0);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    private Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            invalidate();
        }
    };

    private Paint paintCircle;
    private Paint paintDegree,paintHour,paintMinute,paintSecond,paintCenter;

    //启动线程让指针动起来
    private void init(){
        timeThread.start();

//        复用paint
        paintCircle = new Paint();
        paintCircle.setStyle(Paint.Style.STROKE);
        paintCircle.setAntiAlias(true);

        paintDegree = new Paint();
        paintDegree.setAntiAlias(true);

        paintHour = new Paint();
        paintHour.setAntiAlias(true);
        paintHour.setStrokeWidth(15);
        paintMinute = new Paint();
        paintMinute.setAntiAlias(true);
        paintMinute.setStrokeWidth(10);
        paintSecond = new Paint();
        paintSecond.setAntiAlias(true);
        paintSecond.setStrokeWidth(5);

        paintCenter = new Paint();
        paintCenter.setColor(Color.WHITE);

        mPaintDegreeNumber = new Paint();
        mPaintDegreeNumber.setTextAlign(Paint.Align.CENTER);
        mPaintDegreeNumber.setTextSize(DEGRESS_NUMBER_SIZE);
        mPaintDegreeNumber.setFakeBoldText(true);

    }

    /**
     * 计算时针、分针、秒针的长度
     */
    private void reset(){
        float r = (Math.min(getHeight() / 2, getWidth() / 2) - DEFAULT_BORDER_WIDTH / 2);
        secondPointerLength = r * 0.8f;
        minutePointerLength = r * 0.6f;
        hourPointerLength = r * 0.5f;
    }

    /**
     * 根据角度和长度计算线段的起点和终点的坐标
     * @param angle
     * @param length
     * @return
     */
    private float[] calculatePoint(float angle, float length){
        float[] points = new float[4];//0,1是指针反向端点的坐标，2，3是长度未端的坐标
//        if(angle <= 90f){
            points[0] = -(float) Math.sin(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[1] = (float) Math.cos(angle*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
            points[2] = (float) Math.sin(angle*Math.PI/180) * length;
            points[3] = -(float) Math.cos(angle*Math.PI/180) * length;
//        }else if(angle <= 180f){
//            points[0] = -(float) Math.cos((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = -(float) Math.sin((angle-90)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = (float) Math.cos((angle-90)*Math.PI/180) * length;
//            points[3] = (float) Math.sin((angle-90)*Math.PI/180) * length;
//        }else if(angle <= 270f){
//            points[0] = (float) Math.sin((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = -(float) Math.cos((angle-180)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = -(float) Math.sin((angle-180)*Math.PI/180) * length;
//            points[3] = (float) Math.cos((angle-180)*Math.PI/180) * length;
//        }else if(angle <= 360f){
//            points[0] = (float) Math.cos((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[1] = (float) Math.sin((angle-270)*Math.PI/180) * DEFAULT_POINT_BACK_LENGTH;
//            points[2] = -(float) Math.cos((angle-270)*Math.PI/180) * length;
//            points[3] = -(float) Math.sin((angle-270)*Math.PI/180) * length;
//        }
        return points;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        reset();
        //画外圆
        float borderWidth = DEFAULT_BORDER_WIDTH;
        float r = Math.min(getHeight() / 2, getWidth() / 2) - borderWidth / 2;
        paintCircle.setStrokeWidth(borderWidth);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r, paintCircle);

        //画刻度线
        float degreeLength;

        for(int i=0;i<60;i++){
            if(i % 5 == 0){
                paintDegree.setStrokeWidth(6);
                degreeLength = DEFAULT_LONG_DEGREE_LENGTH;
            }else{
                paintDegree.setStrokeWidth(3);
                degreeLength = DEFAULT_SHORT_DEGREE_LENGTH;
            }
            canvas.drawLine(getWidth()/2, Math.abs(getHeight()/2 - r), getWidth()/2,
                    Math.abs(getHeight()/2 - r) + degreeLength, paintDegree);
            canvas.rotate(360/60, getWidth()/2, getHeight()/2);
        }

        //刻度数字
        canvas.translate(getWidth() / 2, getHeight() / 2);
        final float length = r - DEFAULT_LONG_DEGREE_LENGTH - DEGRESS_NUMBER_SIZE / 2 - 15;

        for(int i=1;i<=12;i++){
            float[] temp = calculatePoint(i*30, length);
            canvas.drawText(i+"", temp[2], temp[3] + DEGRESS_NUMBER_SIZE /2-6, mPaintDegreeNumber);
        }

        //画指针

        Calendar now = Calendar.getInstance();
        final float second = now.get(Calendar.SECOND);
        final float minute = now.get(Calendar.MINUTE) + second/60;
        float hour = now.get(Calendar.HOUR_OF_DAY)+minute /60 ;//分钟偏移
//        if(hour>12){
//            //12小时制 +
//            hour -= 12;
//
//            if(hour>12){
//                //12小时制 +
//                hour -= 12;
//            }
//        }


        float[] hourPoints = calculatePoint(hour / 12f * 360, hourPointerLength);
        canvas.drawLine(hourPoints[0], hourPoints[1], hourPoints[2], hourPoints[3], paintHour);
        float[] minutePoints = calculatePoint(minute / 60f * 360, minutePointerLength);
        canvas.drawLine(minutePoints[0], minutePoints[1], minutePoints[2], minutePoints[3], paintMinute);
        float[] secondPoints = calculatePoint(second /60f*360, secondPointerLength);
        canvas.drawLine(secondPoints[0], secondPoints[1], secondPoints[2], secondPoints[3], paintSecond);

        //画圆心
        canvas.drawCircle(0, 0, 2, paintCenter);
    }

    /**
     * 当布局为wrap_content时设置默认长宽
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measure(widthMeasureSpec), measure(heightMeasureSpec));
    }

    private int measure(int origin){
        int result = DEFAULT_MAX_WIDTH_AT_MOST;
        int specMode = MeasureSpec.getMode(origin);
        int specSize = MeasureSpec.getSize(origin);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else{
            if(specMode == MeasureSpec.AT_MOST){
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

}
