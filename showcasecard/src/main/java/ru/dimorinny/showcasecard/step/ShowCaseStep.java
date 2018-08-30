package ru.dimorinny.showcasecard.step;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.position.ViewPosition;

/**
 * One tip object, to be shown as a help tip on the screen.
 */
public class ShowCaseStep {

    /**
     * Position on the screen to point the showcase to, when viewToShowCase is not set.
     */
    private ShowCasePosition position;
    /**
     * Message to display when this item is activated.
     */
    private String message;

    /**
     * A simple step item pointing to the position of viewToShowCase on the screen.
     *
     * @param viewToShowCase showcase will point to this view
     * @param message        message to show
     */
    public ShowCaseStep(@NonNull View viewToShowCase, String message) {
        position = new ViewPosition(viewToShowCase);
        this.message = message;
    }

    /**
     * A simple step item pointing to a position on the screen.
     *
     * @param position position to point to
     * @param message  message to show
     */
    public ShowCaseStep(ShowCasePosition position, String message) {
        this.position = position;
        this.message = message;
    }

    /**
     * A simple step item. Will either point to a view or a position (when viewToShowCase is null).
     * <p>
     * This is mainly a helper constructor for easier usage.
     *
     * @param viewToShowCase   if set, showcase will point to this view
     * @param fallbackPosition position to point to when viewToShowCase is null
     * @param message          message to show
     */
    public ShowCaseStep(@Nullable View viewToShowCase,
                        ShowCasePosition fallbackPosition, String message) {

        if (viewToShowCase == null) {
            position = fallbackPosition;
        } else {
            position = new ViewPosition(viewToShowCase);
        }
        this.message = message;
    }

    /**
     * @return the position this item will point to when activated.
     */
    public ShowCasePosition getPosition() {
        return position;
    }

    public String getMessage() {
        return message;
    }
}
