package ru.dimorinny.showcasecard.step;

import android.view.View;
import android.widget.ScrollView;

/**
 * Created by Frank on 2017/08/17.
 * <p>
 * Handles scrolling to a {@link ShowCaseStepItem} if needed.
 */
public class ShowCaseStepScroller {

    private ScrollView scrollView;
    private int lastTrackedScrollY;

    public ShowCaseStepScroller(ScrollView scrollView) {
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

        View measureView = showCaseItem.getViewToShowCase();
        if (measureView.getParent() != scrollView.getChildAt(0)) {
            // view has another parent in between it and the scrolled content root, measure from the parent:
            measureView = (View) measureView.getParent();
        }
        int scrollToY = measureView.getTop();

        // put the item in the middle:
        scrollToY -= (scrollView.getHeight() / 2);

        if (scrollToY < 0) {
            scrollToY = 0;
        }

        scrollView.smoothScrollBy(0, scrollToY - scrollView.getScrollY());
        detectScrollFinished(() -> onCompleteListener.onComplete());
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
