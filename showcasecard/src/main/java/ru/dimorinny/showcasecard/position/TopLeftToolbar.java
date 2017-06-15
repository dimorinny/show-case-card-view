package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PointF;

import ru.dimorinny.showcasecard.util.ActivityUtils;
import ru.dimorinny.showcasecard.util.NavigationBarUtils;

public class TopLeftToolbar implements ShowCasePosition {

    @Override
    public PointF getPosition(Activity activity) {
        switch (ActivityUtils.getOrientation(activity)) {
            case Configuration.ORIENTATION_LANDSCAPE:
                return new PointF(
                        NavigationBarUtils.navigationBarMarginForLeftOrientation(activity),
                        (float) ActivityUtils.statusBarHeight(activity)
                );
            default:
                return new PointF(
                        0F,
                        ActivityUtils.statusBarHeight(activity)
                );
        }
    }
}
