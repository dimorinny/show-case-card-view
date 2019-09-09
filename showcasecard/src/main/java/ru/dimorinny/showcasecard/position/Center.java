package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.widget.ScrollView;

import ru.dimorinny.showcasecard.util.ActivityUtils;
import ru.dimorinny.showcasecard.util.NavigationBarUtils;

public class Center implements ShowCasePosition {

    @Override
    public PointF getPosition(Activity activity) {
        float y;
        switch (ActivityUtils.getOrientation(activity)) {
            case Configuration.ORIENTATION_LANDSCAPE:
                y = (float) activity.getWindow().getDecorView().getHeight() / 2;
                return new PointF((activity.getWindow().getDecorView().getWidth() -
                    NavigationBarUtils.navigationBarMarginForLeftOrientation(activity) -
                    NavigationBarUtils.navigationBarMarginForRightOrientation(activity)) / 2,
                    y);
            default:
                y = (activity.getWindow().getDecorView().getHeight() - (float) NavigationBarUtils.navigationBarHeight(activity)) / 2;
                return new PointF(activity.getWindow().getDecorView().getWidth() / 2,
                    y);
        }
    }

    @Nullable
    @Override
    public Point getScrollPosition(@Nullable ScrollView scrollView) {
        return null;
    }
}