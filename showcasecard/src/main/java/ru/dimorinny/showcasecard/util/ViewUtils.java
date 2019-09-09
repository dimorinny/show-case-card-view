package ru.dimorinny.showcasecard.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.PointF;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

public class ViewUtils {

    public static int convertDpToPx(View view, int dp) {
        return convertDpToPx(view.getContext(), dp);
    }

    public static int convertDpToPx(Context context, int dp) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return Math.round(dp * (metrics.densityDpi / 160.0f));
    }

    public static PointF getAbsoluteCenterPosition(View view) {
        int[] positions = new int[2];
        view.getLocationOnScreen(positions);
        return new PointF(
                (float) (positions[0] + view.getWidth() / 2),
                (float) (positions[1] + view.getHeight() / 2)
        );
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T findViewWithType(ViewGroup viewGroup, Class<T> clazz) {
        for (int i = 0; i < viewGroup.getChildCount() - 1; i++) {
            View view = viewGroup.getChildAt(i);

            if (view.getClass() == clazz) {
                return (T) view;
            }
        }

        return null;
    }
}
