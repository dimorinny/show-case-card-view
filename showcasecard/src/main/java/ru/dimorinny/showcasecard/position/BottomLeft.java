package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.widget.ScrollView;

import ru.dimorinny.showcasecard.util.ActivityUtils;
import ru.dimorinny.showcasecard.util.NavigationBarUtils;

public class BottomLeft implements ShowCasePosition {

    @Override
    public PointF getPosition(Activity activity) {
        switch (ActivityUtils.getOrientation(activity)) {
            case Configuration.ORIENTATION_LANDSCAPE:
                return new PointF(
                        NavigationBarUtils.navigationBarMarginForLeftOrientation(activity),
                        ((float) activity.getWindow().getDecorView().getHeight())
                );
            default:
                return new PointF(
                        0F,
                        ((float) activity.getWindow().getDecorView().getHeight()) -
                                ((float) NavigationBarUtils.navigationBarHeight(activity))
                );
        }
    }

    @Nullable
    @Override
    public Point getScrollPosition(@Nullable ScrollView scrollView) {
        return null;
    }
}
