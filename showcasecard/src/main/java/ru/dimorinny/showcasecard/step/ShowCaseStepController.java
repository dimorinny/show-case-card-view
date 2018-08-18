package ru.dimorinny.showcasecard.step;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.widget.ScrollView;

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
    /**
     * ScrollView used on all {@link ShowCaseStepItem}'s that dictate
     * scrolling on activation.
     */
    @Nullable
    private ScrollView scrollView;

    private float showCaseRadius;

    /**
     * All items to be displayed.
     */
    private List<ShowCaseStepItem> items = new ArrayList<>();
    private ShowCaseStepScroller showCaseStepScroller;

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
     * @param scrollView scrollView to use on all {@link ShowCaseStepItem}'s that dictate
     *                   scrolling on activation.
     */
    public ShowCaseStepController(@NonNull Fragment fragment, @NonNull ScrollView scrollView) {
        this.fragment = fragment;
        this.scrollView = scrollView;
        this.context = fragment.getContext();

        showCaseStepScroller = new ShowCaseStepScroller(scrollView);
    }

    /**
     * @param scrollView scrollView to use on all {@link ShowCaseStepItem}'s that dictate
     *                   scrolling on activation.
     */
    public ShowCaseStepController(@NonNull Activity activity, @NonNull ScrollView scrollView) {
        this.activity = activity;
        this.scrollView = scrollView;
        this.context = activity;

        showCaseStepScroller = new ShowCaseStepScroller(scrollView);
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

        if (item.doScrollToView()
                && item.getViewToShowCase() != null
                && showCaseStepScroller != null) {
            // try to scroll to the item

            if (showCaseView != null) {
                // hide last card, just show dark overlay for now:
                showCaseView.hideCard();
            }

            // scroll first, after that display the item:
            showCaseStepScroller.scrollToShowCaseStepItem(item,
                    () -> doDisplayTip(item)
            );

        } else {
            // display item right away
            doDisplayTip(item);
        }
    }

    private void doDisplayTip(ShowCaseStepItem item) {

        if (showCaseView != null) {
            // completely remove old view now:
            showCaseView.hide();
        }

        final int myTipIndex = currentlyDisplayedTipIndex;
        showCaseView = new ShowCaseView.Builder(context)
                .withTypedPosition(item.getPosition())
                .withTypedRadius(new Radius(showCaseRadius))
                .dismissOnTouch(false)
                .withTouchListener(() -> {

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

    public Context getContext() {
        return context;
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
