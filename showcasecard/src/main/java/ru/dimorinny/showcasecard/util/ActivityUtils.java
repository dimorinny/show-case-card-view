package ru.dimorinny.showcasecard.util;

import android.app.Activity;

public class ActivityUtils {

    public static int getOrientation(Activity activity) {
        return activity.getResources().getConfiguration().orientation;
    }
}
