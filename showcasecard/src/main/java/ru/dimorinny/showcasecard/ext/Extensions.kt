package ru.dimorinny.showcasecard.ext

import android.app.Activity
import android.content.Context
import android.graphics.PointF
import android.os.Build
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.FrameLayout

fun Context.getOrientation() = resources.configuration.orientation

fun Context.hasNavigationBar(): Boolean {
    val id = resources.getIdentifier("config_showNavigationBar", "bool", "android")
    return id > 0 && resources.getBoolean(id)
}

enum class NavigationBarPosition {
    BOTTOM,
    LEFT,
    RIGHT,
    UNKNOWN
}

fun Activity.navigationBarPosition(): NavigationBarPosition {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && hasNavigationBar()) {
        val gravity = (findViewById(android.R.id.navigationBarBackground)
            .layoutParams as FrameLayout.LayoutParams).gravity
        gravityToNavigationBarPosition(gravity)
    } else {
        NavigationBarPosition.UNKNOWN
    }
}

fun gravityToNavigationBarPosition(gravity: Int): NavigationBarPosition {
    return when (gravity) {
        Gravity.BOTTOM -> NavigationBarPosition.BOTTOM
        Gravity.LEFT -> NavigationBarPosition.LEFT
        Gravity.RIGHT -> NavigationBarPosition.RIGHT
        else -> NavigationBarPosition.UNKNOWN
    }
}

fun Context.navigationBarHeight(): Int {
    val resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

    return if (hasNavigationBar() && resourceId > 0) {
        resources.getDimensionPixelSize(resourceId)
    } else 0
}

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