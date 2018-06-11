package ru.dimorinny.showcasecard

import android.view.View

sealed class ShowCaseRadius {

    abstract fun getRadius(): Float

    class Radius(val value: Float) : ShowCaseRadius() {
        override fun getRadius(): Float = value
    }

    class ViewRadius(
        val view: View,
        val rate: Float = 1F
    ) : ShowCaseRadius() {
        override fun getRadius(): Float = Math.max(view.height, view.width) * rate
    }
}