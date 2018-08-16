package ru.dimorinny.showcasecard.step;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.radius.Radius;

/**
 * Created by Frank on 2017/08/16.
 * <p>
 * Controls the displaying of a list of {@link ShowCaseStepItem}'s one by one.
 */
public class ShowCaseStepController {

    private Context context;
    @Nullable
    private Activity activity;
    @Nullable
    private Fragment fragment;

    private float showCaseRadius;

    /**
     * All items to be displayed.
     */
    private List<ShowCaseStepItem> items = new ArrayList<>();

    /**
     * Index of the currently shown item in the items list.
     */
    private int currentlyDisplayedTipIndex = -1;

    @Nullable
    private ShowCaseView showCaseView;

    public ShowCaseStepController(@NonNull Fragment fragment) {
        this.fragment = fragment;
        this.context = fragment.getContext();
    }

    public ShowCaseStepController(@NonNull Activity activity) {
        this.activity = activity;
        this.context = activity;
    }

    /**
     * Starts the tip-flow. Toggles (on click) through all help items on this page.
     */
    public void start() {

        showCaseRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70,
                context.getResources().getDisplayMetrics());

        tryShowNextTip();
    }

    /**
     * Closes and resets the tips screen.
     */
    public void dismiss() {

        if (showCaseView != null) {
            showCaseView.hide();
        }

        currentlyDisplayedTipIndex = -1;
        items.clear();
    }

    /**
     * Displays the next tip on the screen, or closes the tip screen if no more tips are left.
     */
    private void tryShowNextTip() {

        if (currentlyDisplayedTipIndex >= items.size() - 1) {

            // end of tips reached.
            dismiss();
        } else {

            currentlyDisplayedTipIndex++;
            displayTip(items.get(currentlyDisplayedTipIndex));
        }
    }

    /**
     * Displays one tip on the screen. Tapping the screen will dismiss it.
     *
     * @param item tip details to display.
     */
    private void displayTip(ShowCaseStepItem item) {

        if (item.isScrollToView()) {

            if (showCaseView != null) {
                // remove last view
                showCaseView.removeFromWindow();
            }

            //showCaseDisplayer.scrollToShowcase(item, success -> {
            //    return
            //});// TODO add scrolling to the item

            doDisplayTip(item);
        } else {
            doDisplayTip(item);
        }
    }

    private void doDisplayTip(ShowCaseStepItem item) {

        final int myTipIndex = currentlyDisplayedTipIndex;
        showCaseView = new ShowCaseView.Builder(context)
                .withTypedPosition(item.getPosition())
                .withTypedRadius(new Radius(showCaseRadius))
                .withDismissListener(() -> {

                    if (myTipIndex == currentlyDisplayedTipIndex) {
                        tryShowNextTip();
                    }
                })
                .withContent(item.getMessage())
                .build();

        if (activity == null) {
            showCaseView.show(fragment);
        } else {
            showCaseView.show(activity);
        }
    }

    /**
     * Adds on item to the list of items to display.
     *
     * @param item
     */
    public void addItem(ShowCaseStepItem item) {
        items.add(item);
    }

    /**
     * Sets the list of items to display.
     *
     * @param items
     */
    public void setItems(List<ShowCaseStepItem> items) {
        this.items = items;
    }
}
