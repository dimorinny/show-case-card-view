package ru.dimorinny.showcasecard

import android.app.Activity
import android.content.res.Configuration
import android.graphics.PointF
import android.view.View
import ru.dimorinny.showcasecard.ext.NavigationBarPosition
import ru.dimorinny.showcasecard.ext.getAbsoluteCenterPosition
import ru.dimorinny.showcasecard.ext.getOrientation
import ru.dimorinny.showcasecard.ext.navigationBarHeight
import ru.dimorinny.showcasecard.ext.navigationBarPosition
import ru.dimorinny.showcasecard.ext.statusBarHeight

sealed class ShowCasePosition {

    fun navigationBarMarginForRightOrientation(activity: Activity): Float
        = if (activity.navigationBarPosition() == NavigationBarPosition.LEFT) {
        0F
    } else {
        activity.navigationBarHeight().toFloat()
    }

    fun navigationBarMarginForLeftOrientation(activity: Activity): Float
        = if (activity.navigationBarPosition() == NavigationBarPosition.LEFT) {
        activity.navigationBarHeight().toFloat()
    } else {
        0F
    }

    abstract fun getPosition(activity: Activity): PointF

    class Position(private val position: PointF) : ShowCasePosition() {
        override fun getPosition(activity: Activity): PointF = position
    }

    class ViewPosition(val view: View) : ShowCasePosition() {
        override fun getPosition(activity: Activity): PointF = view.getAbsoluteCenterPosition()
    }

    class TopLeft : ShowCasePosition() {
        override fun getPosition(activity: Activity) = PointF(
            navigationBarMarginForLeftOrientation(activity),
            0F
        )
    }

    class TopRight : ShowCasePosition() {
        override fun getPosition(activity: Activity) = when (activity.getOrientation()) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                PointF(
                    activity.window.decorView.width.toFloat() - navigationBarMarginForRightOrientation(activity),
                    0F
                )
            }
            else -> PointF(activity.window.decorView.width.toFloat(), 0F)
        }
    }

    class BottomLeft : ShowCasePosition() {
        override fun getPosition(activity: Activity) = when (activity.getOrientation()) {
            Configuration.ORIENTATION_LANDSCAPE ->
                PointF(
                    navigationBarMarginForLeftOrientation(activity),
                    activity.window.decorView.height.toFloat()
                )

            else ->
                PointF(
                    0F,
                    activity.window.decorView.height.toFloat() - activity.navigationBarHeight()
                )
        }
    }

    class BottomRight : ShowCasePosition() {
        override fun getPosition(activity: Activity): PointF {
            return when (activity.getOrientation()) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    PointF(
                        activity.window.decorView.width.toFloat() - navigationBarMarginForRightOrientation(activity),
                        activity.window.decorView.height.toFloat()
                    )
                }

                else ->
                    PointF(
                        activity.window.decorView.width.toFloat(),
                        activity.window.decorView.height.toFloat() - activity.navigationBarHeight()
                    )
            }
        }
    }

    class TopRightToolbar : ShowCasePosition() {
        override fun getPosition(activity: Activity) = when (activity.getOrientation()) {
            Configuration.ORIENTATION_LANDSCAPE -> {
                val navigationBarMargin = navigationBarMarginForRightOrientation(activity)
                PointF(
                    activity.window.decorView.width.toFloat() - navigationBarMargin,
                    activity.statusBarHeight().toFloat()
                )
            }
            else -> PointF(activity.window.decorView.width.toFloat(), activity.statusBarHeight().toFloat())
        }
    }

}