package ru.dimorinny.showcasecard.step;

import android.graphics.Point;
import android.support.annotation.NonNull;
import android.widget.ScrollView;

/**
 * Created by Frank on 2017/08/17.
 * <p>
 * Handles scrolling to a {@link ShowCaseStep} if needed.
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
    public void scrollToShowCaseStepItem(ShowCaseStep showCaseItem, final OnCompleteListener onCompleteListener) {

        Point scrollTo = showCaseItem.getPosition().getScrollPosition(scrollView);

        if (scrollTo == null) {
            // no scroll needed
            onCompleteListener.onComplete();
            return;
        }

        scrollView.smoothScrollBy(scrollTo.x, scrollTo.y - scrollView.getScrollY());

        detectScrollFinished(new OnScrollStoppedListener() {
            @Override
            public void onScrollStopped() {
                onCompleteListener.onComplete();
            }
        });
    }

    private void detectScrollFinished(final OnScrollStoppedListener onScrollStoppedListener) {

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
