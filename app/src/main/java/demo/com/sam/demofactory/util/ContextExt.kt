@file:JvmName("Utils")
@file:JvmMultifileClass

package demo.com.sam.demofactory.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.os.Vibrator
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.ViewConfiguration
import android.view.WindowManager


/**
 * dp 转 SP
 */
val Float.dp: Float
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics
    )
val Int.dp: Int
    get() = this.toFloat().dp.toInt()

/**
 * sp转px
 *
 * @param context
 * @param sp
 * @return
 */
fun Context.sp2px(sp: Float): Float {
    return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            sp, resources.displayMetrics
    )
}

fun Context.getScreenWidth(): Int {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    val outMetrics = DisplayMetrics()
    wm.defaultDisplay.getMetrics(outMetrics)
    return outMetrics.widthPixels

}

fun Context.color(@ColorRes res: Int): Int {
    return ContextCompat.getColor(this, res)
}

/**
 * 小震一下
 */
fun Context.vibratorOneShot() {
    vibrator(10) //为啥是10，我看其它地方也都是10，统一体验哈哈（- -.）
}

@SuppressLint("MissingPermission")
fun Context.vibrator(milliseconds: Long){
//    VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE)
    (getSystemService(Context.VIBRATOR_SERVICE) as Vibrator).vibrate(milliseconds) //deprecated但是推荐的接口api level 26
}

val Context.scaledTouchSlop
        get() = ViewConfiguration.get(this).scaledTouchSlop





