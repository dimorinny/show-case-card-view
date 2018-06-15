package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.PointF;

public class Position implements ShowCasePosition {

    private PointF position;

    public Position(PointF position) {
        this.position = position;
    }

    @Override
    public PointF getPosition(Activity activity) {
        return position;
    }
}
