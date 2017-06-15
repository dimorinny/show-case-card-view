package ru.dimorinny.showcasecard.radius;

import android.view.View;

public class ViewRadius implements ShowCaseRadius {

    private View view;
    private float rate = 1F;

    public ViewRadius(View view, float rate) {
        this.view = view;
        this.rate = rate;
    }

    @Override
    public float getRadius() {
        return Math.max(view.getHeight(), view.getWidth()) * rate;
    }

    public View getView() {
        return view;
    }

    public float getRate() {
        return rate;
    }
}
