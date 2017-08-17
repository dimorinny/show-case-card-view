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
     * Position on the screen to point the showcase to, when viewToShowCase is not set.
     */
    private ShowCasePosition fallbackPosition;
    /**
     * Message to display when this item is activated.
     */
    private String message;
    /**
     * View this showcase is attached to. Null if no view (but a position) is directly attached
     * to this item.
     */
    @Nullable
    private View viewToShowCase;
    /**
     * True to scroll to the {@link #viewToShowCase} when this item is being activated.
     */
    private boolean scrollToView;

    /**
     * @param viewToShowCase   if set, showcase will point to this view
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

    @Nullable
    public View getViewToShowCase() {
        return viewToShowCase;
    }
}
