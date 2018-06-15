package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.PointF;

import ru.dimorinny.showcasecard.util.NavigationBarUtils;

public class TopLeft implements ShowCasePosition {

    @Override
    public PointF getPosition(Activity activity) {
        return new PointF(
                NavigationBarUtils.navigationBarMarginForLeftOrientation(activity),
                0F
        );
    }
}
