package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.widget.ScrollView;

public interface ShowCasePosition {

    PointF getPosition(Activity activity);

    @Nullable
    Point getScrollPosition(@Nullable ScrollView scrollView);
}