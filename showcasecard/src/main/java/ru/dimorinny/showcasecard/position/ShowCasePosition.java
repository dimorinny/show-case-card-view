package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.widget.ScrollView;

public interface ShowCasePosition {

    PointF getPosition(Activity activity);

    /**
     * Returns the position that needs to be scrolled to, to put this position
     * in the center of the screen.
     *
     * @param scrollView
     * @return the position to scroll to, or null if no scroll is needed.
     */
    @Nullable
    default Point getScrollPosition(@Nullable ScrollView scrollView) {
        // on default, no scrolling is needed:
        return null;
    }
}