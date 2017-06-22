package ru.dimorinny.showcasecard.radius;

import android.view.View;

public class ViewRadius implements ShowCaseRadius {

    private View view;
    private float rate = .7F;

    public ViewRadius(View view, float rate) {
        this.view = view;
        this.rate = rate;
    }

    public ViewRadius(View view) {
        this.view = view;
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
