package ru.dimorinny.showcasecard.step;

import android.support.annotation.Nullable;
import android.view.View;

import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.position.ViewPosition;

/**
 * One tip object, to be shown as a help tip on the screen.
 */
public class ShowCaseStepItem {

    /**
     * Position on the screen to point the bubble to, when viewToShowCase is not set.
     */
    private ShowCasePosition fallbackPosition;
    private String message;
    private View viewToShowCase;
    /**
     * True to scroll to the view when this item is being activated.
     */
    private boolean scrollToView;

    /**
     * @param viewToShowCase   if set, bubble will point to this view
     * @param fallbackPosition position to use when viewToShowCase is null
     * @param message
     */
    public ShowCaseStepItem(@Nullable View viewToShowCase,
                            ShowCasePosition fallbackPosition, String message) {
        this.viewToShowCase = viewToShowCase;
        this.fallbackPosition = fallbackPosition;
        this.message = message;
    }

    public ShowCaseStepItem(ShowCasePosition position, String message) {
        this.fallbackPosition = position;
        this.message = message;
    }

    public ShowCasePosition getPosition() {
        return viewToShowCase == null ? fallbackPosition : new ViewPosition(viewToShowCase);
    }

    public String getMessage() {
        return message;
    }

    /**
     * True to scroll to the viewToShowCase when this item is being activated.
     */
    public boolean isScrollToView() {
        return scrollToView;
    }

    /**
     * True to scroll to the viewToShowCase when this item is being activated.
     */
    public ShowCaseStepItem setScrollToView(boolean scrollToView) {
        this.scrollToView = scrollToView;
        return this;
    }

    public View getViewToShowCase() {
        return viewToShowCase;
    }
}
