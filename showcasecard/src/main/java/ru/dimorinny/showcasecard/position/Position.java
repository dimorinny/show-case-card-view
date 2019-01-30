package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.widget.ScrollView;

public class Position implements ShowCasePosition {

    private PointF position;

    public Position(PointF position) {
        this.position = position;
    }

    @Override
    public PointF getPosition(Activity activity) {
        return position;
    }

    @Nullable
    @Override
    public Point getScrollPosition(@Nullable ScrollView scrollView) {
        return null;
    }
}
