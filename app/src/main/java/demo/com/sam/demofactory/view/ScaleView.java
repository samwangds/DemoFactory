package demo.com.sam.demofactory.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;

/**
 * Created by Administrator on 2018/1/31 0031.
 */

public class ScaleView extends View implements ScaleGestureDetector.OnScaleGestureListener {

    public ScaleView(Context context) {
        this(context, null);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /** 初始时的缩放值 */
    private float mInitScale = 1;
    /**
     * 双击时 的缩放值
     */
    private float mClickScale = 2;
    /** 最大的缩放值 */
    private float mMaxScale = 4;
    /** 最小的缩放值 */
    private float minScale = 0.5f;
    /** 图片缩放矩阵 */
    private Matrix mMatrix;
    /** 图片缩放手势 */
    private ScaleGestureDetector mScaleGesture;

    // ----------------------------自由移动--------------------------------
    /** 可移动最短距离限制，大于这个值时就可移动 */
    private int mTouchSlop;
    /** 是否可以拖动 */
    private boolean isCanDrag;

    // ----------------------------双击放大--------------------------------
    private GestureDetector mGesture;
    // 是否自动缩放
    private boolean isAutoScale;

    private Paint paint;


    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);


        mMatrix = new Matrix();

        mGesture = new GestureDetector(getContext().getApplicationContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                // 如果正在缩放时，不能放大
                if (isAutoScale) {
                    return true;
                }

                float px = e.getX();
                float py = e.getY();
                // 只有小于最大缩放比例才能放大
                float scale = getScale();
                if (scale != mInitScale) {
                    // mMatrix.postScale(mClickScale/scale, mClickScale/scale,
                    // px, py);
                    postDelayed(new ScaleRunnale(px, py, mInitScale), 16);
                    isAutoScale = true;
                } else {
                    // mMatrix.postScale(mInitScale/scale, mInitScale/scale, px,
                    // py);
                    postDelayed(new ScaleRunnale(px, py, mClickScale), 16);
                    isAutoScale = true;
                }
                // setImageMatrix(mMatrix);
                return true;

            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                performClick();
                return true;
            }
        });
        mGesture.setIsLongpressEnabled(false);
        mScaleGesture = new ScaleGestureDetector(getContext(), this);

        /**
         * 是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件，如viewpager
         * 就是用这个距离来判断用户是否翻页。
         */
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();

    }

    private float cx;
    private float cy;
    private Rect rect = new Rect();

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cx = w / 2;
        cy = h / 2;

        //16:9 按比例算出原始的坐标
        int a = w / 9;
        int b = h / 16;

        a = Math.min(a, b);

        rect.left = (int) (cx - a * 4.5);
        rect.right = rect.left + a * 9;
        rect.top = (int) (cy - a * 8);
        rect.bottom = rect.top + a * 16;


        Shader shader = new LinearGradient( getWidth(), 0, 0, getHeight(),
                Color.BLACK, Color.WHITE, Shader.TileMode.REPEAT);
        paint.setShader(shader);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.concat(mMatrix);
        canvas.drawRect(rect, paint);
//        canvas.concat();
    }


    private float mLastX;
    private float mLastY;
    /** 上次手指的数量 */
    private int mLastPointerCount;

    /** 判断是否检测了x,y轴 */
    private boolean isCheckX;
    private boolean isCheckY;

    private boolean isMoveAction(float dx, float dy) {
        // 求得两点的距离
        return Math.sqrt(dx * dx + dy * dy) > mTouchSlop;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // 把事件传递给双击手势
        mGesture.onTouchEvent(event);
        // 把事件传递给缩放手势
        mScaleGesture.onTouchEvent(event);

        float x = event.getX();
        float y = event.getY();

        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        x /= pointerCount;
        y /= pointerCount;

        // 说明手指改变
        if (mLastPointerCount != pointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }
        mLastPointerCount = pointerCount;

        RectF rectF = getMatrixRectF();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (rectF.width() > getWidth()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (rectF.width() > getWidth()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }
                /**
                 * 如果能移动
                 */
                if (isCanDrag) {

                    isCheckX = isCheckY = true;

                    // 如果图片在控件内，不允许移动
                    if (rectF.width() < getWidth()) {
                        isCheckX = false;
                        dx = 0f;
                    }
                    if (rectF.height() < getHeight()) {
                        isCheckY = false;
                        dy = 0f;
                    }

                    mMatrix.postTranslate(dx, dy);

                    // 移动事检测边界
                    checkSideAndCenterWhenTransate();
                    invalidate();

                }

                mLastX = x;
                mLastY = y;

                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                // 手指全抬起
                mLastPointerCount = 0;
                if (getScale() < mInitScale) {
                    postScaleRunnable(x, y , mInitScale);
                }
                break;
        }

        return true;
    }




    private void checkSideAndCenterWhenTransate() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0f;
        float deltaY = 0f;
        int width = getWidth();
        int height = getHeight();

        if (rectF.top > 0 && isCheckY) {
            deltaY = -rectF.top;// 往上边移动
        }
        if (rectF.bottom < height && isCheckY) {
            deltaY = height - rectF.bottom;// 往底部移动
        }

        if (rectF.left > 0 && isCheckX) {
            deltaX = -rectF.left;// 往左边移动
        }
        if (rectF.right < width && isCheckX) {
            deltaX = width - rectF.right;// 往右边移动
        }
        // 移动
        mMatrix.postTranslate(deltaX, deltaY);
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        if (isAutoScale) {
            return false;
        }
        // 当前Scale
        float currentScale = getScale();
        //相对scale 值； 缩放因子，>0表示正在放大，<0表示正在缩小
        float scaleFactor = detector.getScaleFactor();
        //目标实际scale 值
        float intentScale = scaleFactor * currentScale;

        // 进行缩放范围的控制
        // 判断，如果<最大缩放值，表示可以放大，如果》最小缩放，说明可以缩小
        if (intentScale <= mMaxScale) {
            // currentScale 变小时， intentScale变小
            if (intentScale < minScale) {
                postScaleRunnable(cx, cy , mInitScale);
                return true;
            }

            // currentScale 变大时， intentScale变大
            if (intentScale > mMaxScale) {
                // intentScale * currentScale = mMaxScale ;
                scaleFactor = mMaxScale / currentScale;
            }

            // 以控件为中心缩放
            // mMatrix.postScale(intentScale, intentScale, getWidth()/2,
            // getHeight()/2);
            // 以手势为中心缩放
            mMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(), detector.getFocusY());

            // 检测边界与中心点
            checkSideAndCenterWhenScale();
            invalidate();

        }

        return true;
    }

    private void postScaleRunnable(float x, float y, float targetScale) {
        postDelayed(new ScaleRunnale(x, y, targetScale), 16);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return !isAutoScale;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }

    /**
     * 获得缩放值
     *
     * @return
     */
    public float getScale() {
        /**
         * xscale xskew xtrans yskew yscale ytrans 0 0 0
         */
        float[] values = new float[9];
        mMatrix.getValues(values);
        return values[Matrix.MSCALE_X];
    }

    private class ScaleRunnale implements Runnable {
        // 放大值
        private static final float BIGGER = 1.02f;
        // 缩小值
        private static final float SMALLER = 0.98f;
        private float x;
        private float y;
        private float mTargetScale;
        private float mTempScale;

        public ScaleRunnale(float x, float y, float mTargetScale) {
            super();
            this.x = x;
            this.y = y;
            this.mTargetScale = mTargetScale;
            float scale = getScale();
            if (scale < mTargetScale) {
                mTempScale = BIGGER;
            } else if (scale > mTargetScale) {
                mTempScale = SMALLER;
            }
        }


        @Override
        public void run() {
            // 先进行缩放
            mMatrix.postScale(mTempScale, mTempScale, x, y);
            checkSideAndCenterWhenScale();
            invalidate();

            float currentScale = getScale();

            // 如果想放大，并且当前的缩放值小于目标值
            if ((mTempScale > 1.0f && currentScale < mTargetScale)
                    || (mTempScale < 1.0f && currentScale > mTargetScale)) {
                // 递归执行run方法
                postDelayed(this, 16);
            } else {
                float scale = mTargetScale / currentScale;
                mMatrix.postScale(scale, scale, x, y);
                checkSideAndCenterWhenScale();
                invalidate();
                isAutoScale = false;
            }
        }
    }

    private void checkSideAndCenterWhenScale() {
        RectF rectF = getMatrixRectF();
        float deltaX = 0f;
        float deltaY = 0f;
        int width = getWidth();
        int height = getHeight();

        // 情况1， 如果图片的宽度大于控件的宽度
        if (rectF.width() >= width) {
            if (rectF.left > 0) {
                deltaX = -rectF.left;// 如果图片没有左边对齐，就往左边移动
            }
            if (rectF.right < width) {
                deltaX = width - rectF.right;// 如果图片没有右边对齐，就往右边移动
            }
        }
        // 情况2， 如果图片的宽度大于控件的宽度
        if (rectF.height() >= height) {
            if (rectF.top > 0) {
                deltaY = -rectF.top;//
            }
            if (rectF.bottom < height) {
                deltaY = height - rectF.bottom;// 往底部移动
            }
        }

        // 情况3,如图图片在控件内，则让其居中
        if (rectF.width() < width) {
            // deltaX = width/2-rectF.left - rectF.width()/2;
            // 或
            deltaX = width / 2f - rectF.right + rectF.width() / 2f;
        }

        if (rectF.height() < height) {
            deltaY = height / 2f - rectF.bottom + rectF.height() / 2f;
        }

        mMatrix.postTranslate(deltaX, deltaY);
    }

    /**
     * 获得图片缩放后的矩阵
     *
     * @return
     */
    public RectF getMatrixRectF() {
        RectF rectF = new RectF(rect);
        mMatrix.mapRect(rectF);
        return rectF;
    }


}