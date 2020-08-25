package demo.com.sam.demofactory.util


/**
 * 数字扩展方法集
 * @author SamWang(wds1@meitu.com)
 * @date 2019-11-20
 */

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

/**
 * 返回值限定范围在[min],[max]之间
 */
fun Long.fitRange(min: Long, max: Long): Long {
    return when {
        this < min -> min
        this > max -> max
        else -> this
    }
}