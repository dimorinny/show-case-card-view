package ru.dimorinny.showcasecard.util;

import android.view.View;
import android.view.ViewTreeObserver;

import java.util.List;

public class MeasuredUtils {

    public interface OnMeasuredHandler {
        void onMeasured();
    }

    public static void afterMeasured(final View view, final OnMeasuredHandler handler) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (view.getMeasuredWidth() > 0 && view.getMeasuredHeight() > 0) {
                    view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    handler.onMeasured();
                }
            }
        });
    }

    public static void afterOrAlreadyMeasuredViews(final List<View> views, final OnMeasuredHandler handler) {
        final int[] count = {views.size()};

        if (count[0] <= 0) handler.onMeasured();

        for (View view : views) {
            afterOrAlreadyMeasured(view, new OnMeasuredHandler() {
                @Override
                public void onMeasured() {
                    count[0]--;
                    if (count[0] <= 0) handler.onMeasured();
                }
            });
        }
    }

    private static void afterOrAlreadyMeasured(final View view, final OnMeasuredHandler handler) {
        if (view.getMeasuredWidth() != 0 && view.getMeasuredHeight() != 0) {
            handler.onMeasured();
        } else {
            afterMeasured(view, new OnMeasuredHandler() {
                @Override
                public void onMeasured() {
                    handler.onMeasured();
                }
            });
        }
    }
}
