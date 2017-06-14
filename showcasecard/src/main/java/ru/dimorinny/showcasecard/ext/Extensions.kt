package ru.dimorinny.showcasecard.ext

import android.content.Context
import android.graphics.PointF
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver

fun Context.statusBarHeight(): Int {
    val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        return resources.getDimensionPixelSize(resourceId)
    }
    return 0
}

@Suppress("UNCHECKED_CAST")
fun <T> ViewGroup.findViewWithType(type: Class<T>): T? =
    (0..childCount - 1)
        .map { getChildAt(it) }
        .filter { type.isInstance(it) }
        .map { it as T }
        .firstOrNull()

fun convertDpToPx(context: Context, dp: Int): Int {
    val resources = context.resources
    val metrics = resources.displayMetrics
    return Math.round(dp * (metrics.densityDpi / 160.0f))
}

fun View.convertDpToPx(dp: Int): Int = convertDpToPx(context, dp)

inline fun <T : View> T.afterMeasured(crossinline f: T.() -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

        override fun onGlobalLayout() {
            if (measuredWidth > 0 && measuredHeight > 0) {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                f()
            }
        }
    })
}

inline fun <T : View> T.afterOrAlreadyMeasured(crossinline f: T.() -> Unit) {
    if (measuredWidth != 0 && measuredHeight != 0) {
        f()
    } else {
        afterMeasured { f() }
    }
}

inline fun afterOrAlreadyMeasuredViews(vararg views: View, crossinline f: () -> Unit) {
    var count = views.size

    if (count <= 0) f()

    views.forEach { view ->
        if (view.measuredWidth != 0 && view.measuredHeight != 0) {
            count--
            if (count <= 0) f()
        } else {
            view.afterOrAlreadyMeasured {
                count--
                if (count <= 0) f()
            }
        }
    }
}

fun View.getAbsoluteCenterPosition(): PointF {
    val positions = IntArray(2)
    getLocationOnScreen(positions)
    return PointF((positions[0] + width / 2).toFloat(), (positions[1] + height / 2).toFloat())
}