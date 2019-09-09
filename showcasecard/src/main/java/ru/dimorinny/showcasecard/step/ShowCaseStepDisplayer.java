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
import ru.dimorinny.showcasecard.util.ViewUtils;

public class ShowCaseStepDisplayer {

    private Context context;

    @Nullable
    private Activity activity;
    @Nullable
    private Fragment fragment;
    @Nullable
    private ScrollView scrollView;

    private List<ShowCaseStep> items = new ArrayList<>();

    @Nullable
    private ShowCaseStepScroller showCaseStepScroller;

    private int currentlyDisplayedTipIndex = -1;

    @Nullable
    private ShowCaseView showCaseView;

    private ShowCaseStepDisplayer(
        @Nullable Activity activity,
        @Nullable Fragment fragment,
        @Nullable ScrollView scrollView
    ) {
        this.activity = activity;
        this.fragment = fragment;
        this.scrollView = scrollView;

        //noinspection ConstantConditions
        this.context = activity != null ? activity : fragment.getContext();

        if (scrollView != null) {
            showCaseStepScroller = new ShowCaseStepScroller(scrollView);
        }
    }

    public void start() {
        tryShowNextTip();
    }

    public void dismiss() {

        if (showCaseView != null) {
            showCaseView.hide();
        }

        currentlyDisplayedTipIndex = -1;
        items.clear();
    }

    private void tryShowNextTip() {

        if (!isContextActive()) {
            return;
        }

        if (currentlyDisplayedTipIndex >= items.size() - 1) {

            // end of tips reached.
            dismiss();
        } else {

            currentlyDisplayedTipIndex++;
            displayTip(items.get(currentlyDisplayedTipIndex));
        }
    }

    private void displayTip(final ShowCaseStep item) {

        if (item.getPosition().getScrollPosition(scrollView) != null
            && showCaseStepScroller != null) {
            // try to scroll to the item

            if (showCaseView != null) {
                // hide last card, just show dark overlay for now:
                showCaseView.hideCard();
            }

            // scroll first, after that display the item:
            showCaseStepScroller.scrollToShowCaseStepItem(
                item,
                new ShowCaseStepScroller.OnCompleteListener() {
                    @Override
                    public void onComplete() {
                        doDisplayTip(item);
                    }
                }
            );

        } else {
            // display item right away
            doDisplayTip(item);
        }
    }

    private void doDisplayTip(ShowCaseStep item) {

        if (!isContextActive()) {
            return;
        }

        if (showCaseView != null) {
            // completely remove old view now:
            showCaseView.hide();
        }

        final int myTipIndex = currentlyDisplayedTipIndex;
        showCaseView = new ShowCaseView.Builder(context)
            .withTypedPosition(item.getPosition())
            .withTypedRadius(item.getRadius())
            .dismissOnTouch(false)
            .withTouchListener(new ShowCaseView.TouchListener() {
                @Override
                public void onTouchEvent() {
                    if (myTipIndex == currentlyDisplayedTipIndex) {
                        tryShowNextTip();
                    }
                }
            })
            .withContent(item.getMessage())
            .withColor(item.getColor())
            .build();

        if (activity == null) {
            showCaseView.show(fragment);
        } else {
            showCaseView.show(activity);
        }
    }

    @SuppressWarnings("unused")
    public Context getContext() {
        return context;
    }

    /**
     * Adds on item to the list of items to display.
     *
     * @param item
     */
    @SuppressWarnings("unused")
    public void addStep(ShowCaseStep item) {
        items.add(item);
    }

    /**
     * Sets the list of items to display.
     *
     * @param items
     */
    @SuppressWarnings("unused")
    public void setSteps(List<ShowCaseStep> items) {
        this.items = items;
    }

    /**
     * Returns true if the attached Context is still active / not shutting down.
     */
    private boolean isContextActive() {
        if (fragment != null) {
            return fragment.isAdded();
        } else if (activity != null) {
            return !activity.isFinishing();
        }
        return true;
    }

    public static class Builder {

        @Nullable
        private Activity activity;
        @Nullable
        private Fragment fragment;
        /**
         * ScrollView used on all {@link ShowCaseStep}'s that used to scroll to the View
         * on activation.
         */
        @Nullable
        private ScrollView scrollView;

        private List<ShowCaseStep> items = new ArrayList<>();

        @SuppressWarnings("unused")
        public Builder(@NonNull Fragment fragment) {
            this.fragment = fragment;
        }

        @SuppressWarnings("unused")
        public Builder(@NonNull Activity activity) {
            this.activity = activity;
        }

        /**
         * ScrollView used on all {@link ShowCaseStep}'s to scroll to the View
         * on activation.
         */
        public Builder withScrollView(@Nullable ScrollView scrollView) {
            this.scrollView = scrollView;
            return this;
        }

        /**
         * Adds on item to the list of items to display.
         *
         * @param item
         */
        @SuppressWarnings("unused")
        public Builder addStep(ShowCaseStep item) {
            items.add(item);
            return this;
        }

        @SuppressWarnings("unused")
        public ShowCaseStepDisplayer build() {

            ShowCaseStepDisplayer stepController =
                new ShowCaseStepDisplayer(activity, fragment, scrollView);

            stepController.setSteps(items);

            return stepController;
        }
    }
}
