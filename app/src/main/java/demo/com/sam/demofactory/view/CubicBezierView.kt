package demo.com.sam.demofactory.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import demo.com.sam.demofactory.util.dp
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * 三阶贝塞尔曲线 view
 * 可以有N个操作点，操作点按 X 坐标排序，X 坐标无法移到位置外面
 * 操作点通过 [points] 记录，更新后需要调用 [updatePoint]
 * 设置 [pointEvent] 可以监听用户操作点的更新和其它位置的更新
 *
 * @author SamWang
 * @date 2019-11-18
 */
class CubicBezierView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr)  {

    private val gradientColor = intArrayOf(
        Color.parseColor("#ff5093ff"),
        Color.parseColor("#ff34dcff"),
        Color.parseColor("#fff9b0d8")
    )

    /**
     * 曲线点显示的位置 ，顶点为 view 的左上角
     * 百分比: x,y [0.0, 1.0]
     * 真实坐标： width, height
     */
    val points: MutableList<PointF> = mutableListOf()
    private val PointF.width
            get() = this.x * rect.width() + radius
    private val PointF.height
        get() = this.y * rect.height() + radius

    private fun PointF.setWidth(width: Float) {
        this.x = width2ScaleX(width)
    }
    private fun width2ScaleX(width: Float):Float {
        return(width - radius) / rect.width()
    }
    private fun PointF.setHeight(height: Float) {
        this.y = height2ScaleY(height)
    }
    private fun height2ScaleY(height: Float):Float {
        return (height - radius) / rect.height()
    }
    /**
     * 曲线路径
     */
    private val curvePath = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pathDash.reset()
        val realH = height - radius - radius
        pathDash.moveTo(radius, radius + realH * 0.25f)
        pathDash.rLineTo(width.toFloat() - radius - radius, 0f)
        pathDash.moveTo(radius, radius + realH * 0.75f)
        pathDash.rLineTo(width.toFloat() - radius - radius, 0f)

        rect.set(radius, radius, width.toFloat() - radius, height.toFloat() - radius)

        curveShader = LinearGradient(
            rect.left, 0f, rect.right, 0f,
            gradientColor, floatArrayOf(0f, 0.39f, 1f), Shader.TileMode.CLAMP
        )

        updatePoint()
        invalidate()
    }

    private val baseLineColor = Color.parseColor("#31FFFFFF") //基线颜色
    private val baseLineWidth = 0.5f.dp //基线宽度
    private val paintBaseLine = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = baseLineWidth
        style = Paint.Style.STROKE
        color = baseLineColor
    }
    private val dashPathEffect =
        DashPathEffect(floatArrayOf(4f.dp, 4f.dp), 0f)

    private val bgCornerRadius = 4f.dp
    private val rect = RectF()
    private val pathDash = Path()
    private val radius = 8f.dp

    private val contentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 1.5f.dp
        color = Color.WHITE
    }
    private var curveShader: LinearGradient? = null


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (width == 0 || height == 0) {
            return
        }

        //画基线
        paintBaseLine.pathEffect = null
        canvas.drawRoundRect(rect, bgCornerRadius, bgCornerRadius, paintBaseLine)
        canvas.drawLine(rect.left, height * 0.5f, rect.right, height * 0.5f, paintBaseLine)
        paintBaseLine.pathEffect = dashPathEffect
        canvas.drawPath(pathDash, paintBaseLine)

        //曲线
        contentPaint.style = Paint.Style.STROKE
        contentPaint.shader = curveShader
        canvas.drawPath(curvePath, contentPaint)

        //变速点
        contentPaint.shader = null
        contentPaint.style = Paint.Style.FILL
        for (item in points) {
            canvas.drawCircle(item.width, item.height, radius, contentPaint)
        }

    }



    /**
     * 更改 [points]后调用
     * 更新变速点位置
     */
    fun updatePoint() {
        updateCurvePath()
        invalidate()
    }

    /**
     * 更新曲线路径
     */
    private fun updateCurvePath() {
        curvePath.reset()
        if (points.isEmpty()) {
            return
        }
        var firstPoint = points[0]
        curvePath.moveTo(firstPoint.width , firstPoint.height )
        var secondPoint: PointF
        for (i in 1 until points.size) {
            secondPoint = points[i]
            val cx = (firstPoint.width + secondPoint.width)  / 2
            curvePath.cubicTo(cx, firstPoint.height , cx, secondPoint.height , secondPoint.width  , secondPoint.height )
            firstPoint = secondPoint
        }


    }


    private val gestureDetector: GestureDetector by lazy(LazyThreadSafetyMode.NONE) {
        GestureDetector(context, gestureListener)
    }

    /**
     * 两点距离
     */
    private fun distance(x1: Float, y1: Float, x2: Float, y2: Float): Float {
        return sqrt((x1 - x2).toDouble().pow(2.0) + (y1 - y2).toDouble().pow(2.0)).toFloat()
    }

    var downPointIndex = -1 //按下第几个点，-1为按下的不是点
    /**
     * 滑动、双击监听
     */
    private val gestureListener = object : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            downPointIndex = -1
            val touchArea = radius * 2 //加大点击区域
            for ((index, item) in points.withIndex()) {
                if (distance(item.width, item.height, e.x, e.y) <= touchArea) {
                    downPointIndex = index //可用于点击位置通知或处理
                }
            }

            return true
        }

        override fun onSingleTapUp(event: MotionEvent): Boolean {
            pointEvent?.onEvent(width2ScaleX(fitX(event.x)), height2ScaleY(fitY(event.y)), -1)
            return true
        }

        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            val event = e2 ?: return false
            if (downPointIndex in 0 until points.size) {
                val point = points.getOrNull(downPointIndex) ?: return false

                val minX = points.getOrNull(downPointIndex - 1)?.width ?: 0 + radius
                val maxX = points.getOrNull(downPointIndex + 1)?.width ?: width - radius
                val x = event.x.fitRange(minX, maxX)

                point.setWidth(x)
                point.setHeight(fitY(event.y))
                pointEvent?.onEvent(point.x, point.y, downPointIndex)
                updateCurvePath()
                invalidate()
                return true
            }

            pointEvent?.onEvent(width2ScaleX(fitX(event.x)), height2ScaleY(fitY(event.y)), -1)
            invalidate()
            return true
        }

    }

    /**
     * 使x合适
     */
    private fun fitX(x: Float): Float {
       return x.fitRange(rect.left, rect.right)
    }

    private fun fitY(y: Float): Float {
        return y.fitRange(rect.top, rect.bottom)
    }


    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }
    var pointEvent: Event? = null

    interface Event {
        /**
         * 事件更新回调
         * @param scaleX x 比例坐标
         * @param scaleY y 比例坐标
         * @param pointIndex 操作哪个点，-1表示未操作点
         *
         */
        fun onEvent(scaleX: Float, scaleY: Float, pointIndex: Int)
    }

    // ----- 工具方法
    /**
     * dp 转 SP
     */
    val Float.dp: Float
        get() = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
        )

    /**
     * 返回值限定范围在[min],[max]之间
     */
    fun Float.fitRange(min: Float, max: Float): Float {
        return when {
            this < min -> min
            this > max -> max
            else -> this
        }
    }

}