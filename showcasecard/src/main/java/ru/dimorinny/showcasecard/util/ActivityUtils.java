package ru.dimorinny.showcasecard.util;

import android.app.Activity;

public class ActivityUtils {

    public static int getOrientation(Activity activity) {
        return activity.getResources().getConfiguration().orientation;
    }

    public static int statusBarHeight(Activity activity) {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            return activity.getResources().getDimensionPixelSize(resourceId);
        }

        return 0;
    }
}
