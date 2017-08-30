package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ScrollView;

import ru.dimorinny.showcasecard.util.ViewUtils;

public class ViewPosition implements ShowCasePosition {

    private View view;

    public ViewPosition(View view) {
        this.view = view;
    }

    @Override
    public PointF getPosition(Activity activity) {
        return ViewUtils.getAbsoluteCenterPosition(view);
    }

    @Nullable
    @Override
    public Point getScrollPosition(@Nullable ScrollView scrollView) {

        if (scrollView == null || scrollView.findViewById(view.getId()) == null) {
            // scrollview not set, or child is not part of the scrollview content: do not scroll.
            return null;
        }

        // get the top of the item relative to the ScrollView:
        Rect offsetViewBounds = new Rect();
        view.getDrawingRect(offsetViewBounds);
        scrollView.offsetDescendantRectToMyCoords(view, offsetViewBounds);
        int relativeTop = offsetViewBounds.top;

        // put the item in the middle of the screen:
        int scrollToY = relativeTop - (scrollView.getHeight() / 2);
        if (scrollToY < 0) {
            scrollToY = 0;
        }

        return new Point(scrollView.getScrollX(), scrollToY);
    }

    public View getView() {
        return view;
    }
}
