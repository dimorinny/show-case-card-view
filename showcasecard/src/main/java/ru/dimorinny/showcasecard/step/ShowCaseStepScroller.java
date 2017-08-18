package ru.dimorinny.showcasecard.step;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Frank on 2017/08/17.
 * <p>
 * Handles scrolling to a {@link ShowCaseStepItem} if needed.
 */
public class ShowCaseStepScroller {

    @NonNull
    private ScrollView scrollView;
    private int lastTrackedScrollY;

    public ShowCaseStepScroller(@NonNull ScrollView scrollView) {
        this.scrollView = scrollView;
    }

    interface OnCompleteListener {

        void onComplete();
    }

    interface OnScrollStoppedListener {

        void onScrollStopped();
    }

    /**
     * Scrolls the given scrollView so that showCaseItem is completely in view.
     *
     * @param showCaseItem
     * @param onCompleteListener
     */
    public void scrollToShowCaseStepItem(ShowCaseStepItem showCaseItem, OnCompleteListener onCompleteListener) {

        int scrollToY = getPositionYToScrollTo(showCaseItem);

        if (scrollToY < 0) {
            scrollToY = 0;
        }

        scrollView.smoothScrollBy(0, scrollToY - scrollView.getScrollY());

        detectScrollFinished(() -> onCompleteListener.onComplete());
    }

    /**
     * Returns the Y position we have to scroll to to put this showCaseItem in the middle of the screen.
     *
     * @param showCaseItem showcase item to calculate for.
     * @return positionY to scroll to
     */
    private int getPositionYToScrollTo(ShowCaseStepItem showCaseItem) {

        View view = showCaseItem.getViewToShowCase();

        if (view == null) {
            Log.e("ShowCase", "item.scrollToView is true, but you attached a NULL view. Set a view on this item if you want to scroll.");
            return 0;
        }

        // get the top of the item relative to the ScrollView:
        Rect offsetViewBounds = new Rect();
        view.getDrawingRect(offsetViewBounds);
        scrollView.offsetDescendantRectToMyCoords(view, offsetViewBounds);
        int relativeTop = offsetViewBounds.top;

        // put the item in the middle of the screen:
        int scrollToY = relativeTop - (scrollView.getHeight() / 2);

        return scrollToY;
    }

    private void detectScrollFinished(OnScrollStoppedListener onScrollStoppedListener) {

        lastTrackedScrollY = scrollView.getScrollY();

        final int checkIntervalMs = 50;

        scrollView.postDelayed(new Runnable() {

            public void run() {

                int newPosition = scrollView.getScrollY();
                if (lastTrackedScrollY - newPosition == 0) {

                    onScrollStoppedListener.onScrollStopped();

                } else {
                    lastTrackedScrollY = scrollView.getScrollY();
                    scrollView.postDelayed(this, checkIntervalMs);
                }
            }
        }, checkIntervalMs);
    }
}
