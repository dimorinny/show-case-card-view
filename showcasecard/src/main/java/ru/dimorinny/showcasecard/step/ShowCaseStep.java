package ru.dimorinny.showcasecard.step;

import android.content.Context;

import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.radius.Radius;
import ru.dimorinny.showcasecard.radius.ShowCaseRadius;
import ru.dimorinny.showcasecard.util.ViewUtils;

public class ShowCaseStep {

    private ShowCasePosition position;
    private ShowCaseRadius radius;
    private String message;

    public ShowCasePosition getPosition() {
        return position;
    }

    public ShowCaseRadius getRadius() {
        return radius;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {

        private final static int DEFAULT_RADIUS_DP = 70;

        private ShowCaseRadius radius;
        private ShowCasePosition position;
        private String message;

        public ShowCaseStep.Builder withTypedRadius(ShowCaseRadius radius) {
            this.radius = radius;
            return this;
        }

        public ShowCaseStep.Builder withTypedPosition(ShowCasePosition position) {
            this.position = position;
            return this;
        }

        public ShowCaseStep.Builder withMessage(String message) {
            this.message = message;
            return this;
        }

        public ShowCaseStep build(Context context) {
            checkRequiredFields();

            ShowCaseStep step = new ShowCaseStep();
            if (radius == null) {
                radius = new Radius(
                    ViewUtils.convertDpToPx(context, DEFAULT_RADIUS_DP)
                );
            }
            step.position = position;
            step.radius = radius;
            step.message = message;

            return step;
        }

        private void checkRequiredFields() {
            if (position == null) {
                throw new IllegalArgumentException(
                    "position is required field for ShowCaseStep builder"
                );
            }

            if (message == null) {
                throw new IllegalArgumentException(
                    "message is required field for ShowCaseStep builder"
                );
            }
        }
    }
}
