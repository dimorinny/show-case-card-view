package ru.dimorinny.showcasecard.position;

import android.app.Activity;
import android.graphics.PointF;
import android.view.View;

import ru.dimorinny.showcasecard.util.ViewUtils;

public class ViewPosition implements ShowCasePosition {

    private View view;

    public ViewPosition(View view) {
        this.view = view;
    }

    @Override
    public PointF getPosition(Activity activity) {
        return ViewUtils.getAbsoluteCenterPosition(view);
    }

    public View getView() {
        return view;
    }
}
