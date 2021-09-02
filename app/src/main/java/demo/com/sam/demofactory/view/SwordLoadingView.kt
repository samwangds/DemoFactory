package demo.com.sam.demofactory.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.RotateAnimation
import kotlin.math.min

/**
 *
 * 剑气加载，灵感来源：https://juejin.cn/post/7001779766852321287
 * 文章：https://mp.weixin.qq.com/s/YS5CPEb48fun2d7kvR8otw
 *
 * @author SamWang(wds0405@qq.com)
 * @date 2021/9/2
 */
class SwordLoadingView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        color = Color.WHITE
    }

    val anim = ValueAnimator.ofFloat(0f, -360f).apply {
        // Z 轴是逆时针，取负数，得到顺时针的旋转
        interpolator = null
        repeatCount = RotateAnimation.INFINITE
        duration = 1000

        addUpdateListener {
            invalidate()
        }
    }

    var radius = 0f
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = min(w, h) / 3f
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.BLACK)
        drawSword(canvas,35f, -45f, 0f)
        drawSword(canvas,50f, 10f, 120f)
        drawSword(canvas,35f, 55f, 240f)
    }

    private val camera = Camera()
    private val rotateMatrix = Matrix()

    val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

    private fun drawSword(canvas: Canvas, rotateX: Float, rotateY: Float, startValue: Float) {
        val layerId =
            canvas.saveLayer(0f, 0f, width.toFloat(), height.toFloat(), null, Canvas.ALL_SAVE_FLAG)
        rotateMatrix.reset()
        camera.save()
        camera.rotateX(rotateX)
        camera.rotateY(rotateY)
        camera.rotateZ(anim.animatedValue as Float + startValue)
        camera.getMatrix(rotateMatrix)
        camera.restore()

        val halfW = width / 2f
        val halfH = height / 2f

        rotateMatrix.preTranslate(-halfW, -halfH)
        rotateMatrix.postTranslate(halfW, halfH)
        canvas.concat(rotateMatrix)
        canvas.drawCircle(halfW, halfH, radius, paint)
        paint.xfermode = xfermode
        canvas.drawCircle(halfW, halfH - 0.05f * radius, radius * 1.01f, paint)
        canvas.restoreToCount(layerId)
        paint.xfermode = null
    }

}