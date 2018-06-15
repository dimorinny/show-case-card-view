package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.PointF;

import ru.dimorinny.showcasecard.util.ActivityUtils;
import ru.dimorinny.showcasecard.util.NavigationBarUtils;

public class TopRightToolbar implements ShowCasePosition {

    @Override
    public PointF getPosition(Activity activity) {
        float width = (float) activity.getWindow().getDecorView().getWidth();

        switch (ActivityUtils.getOrientation(activity)) {
            case Configuration.ORIENTATION_LANDSCAPE:
                return new PointF(
                        width - NavigationBarUtils.navigationBarMarginForRightOrientation(activity),
                        (float) ActivityUtils.statusBarHeight(activity)
                );
            default:
                return new PointF(
                        width,
                        ActivityUtils.statusBarHeight(activity)
                );
        }
    }
}
