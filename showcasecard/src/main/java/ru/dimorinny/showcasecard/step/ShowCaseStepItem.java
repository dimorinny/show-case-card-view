package ru.dimorinny.showcasecard.step;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import ru.dimorinny.showcasecard.position.Center;
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
    private boolean scrollToView = false;

    /**
     * A simple step item. viewToShowCase must be on the screen.
     *
     * @param viewToShowCase showcase will point to this view
     * @param message        message to show
     */
    public ShowCaseStepItem(@NonNull View viewToShowCase, String message) {
        this.viewToShowCase = viewToShowCase;
        this.message = message;
    }

    /**
     * A simple step item. On activation, a scroll may occur.
     *
     * @param viewToShowCase showcase will point to and scroll to this view (if scrollToView is true) when activated.
     * @param scrollToView   true when you wish to scroll to this view when this showcase item is activated. Note
     *                       that you'll have to provide the {@link ShowCaseStepController} with a ScrollView for this.
     * @param message        message to show
     */
    public ShowCaseStepItem(@NonNull View viewToShowCase, String message, boolean scrollToView) {
        this.viewToShowCase = viewToShowCase;
        this.scrollToView = scrollToView;
        this.message = message;
    }

    /**
     * A simple step item. Will either point to a view or a position (when viewToShowCase is null).
     *
     * @param viewToShowCase   if set, showcase will point to this view
     * @param fallbackPosition position to point to when viewToShowCase is null
     * @param message          message to show
     */
    public ShowCaseStepItem(@Nullable View viewToShowCase,
                            ShowCasePosition fallbackPosition, String message) {
        this.viewToShowCase = viewToShowCase;
        this.fallbackPosition = fallbackPosition;
        this.message = message;
    }

    /**
     * A simple step item pointing to a position on the screen.
     *
     * @param position position to point to
     * @param message  message to show
     */
    public ShowCaseStepItem(ShowCasePosition position, String message) {
        this.fallbackPosition = position;
        this.message = message;
    }

    /**
     * @return the position this item will point to when activated. Can either be a view's position
     * or a abstract position like {@link Center}.
     */
    public ShowCasePosition getPosition() {
        return viewToShowCase == null ? fallbackPosition : new ViewPosition(viewToShowCase);
    }

    public String getMessage() {
        return message;
    }

    /**
     * True to scroll to the viewToShowCase when this item is being activated.
     */
    public boolean doScrollToView() {
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
