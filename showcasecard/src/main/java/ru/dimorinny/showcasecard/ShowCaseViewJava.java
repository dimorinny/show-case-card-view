package ru.dimorinny.showcasecard;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.PointF;
import android.support.annotation.ColorRes;
import android.widget.TextView;

import ru.dimorinny.showcasecard.position.Position;
import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.radius.Radius;
import ru.dimorinny.showcasecard.radius.ShowCaseRadius;

public class ShowCaseViewJava {

    public interface DismissListener {
        void onDismiss();
    }

    public static class Builder {

        private Activity activity;

        @ColorRes
        private int color = R.color.black65;
        private ShowCaseRadius radius = new Radius(128F);
        private TextView contentView;
        private String contentText;
        private DismissListener dismissListener;
        private ShowCasePosition position = new Position(
                new PointF(0F, 0F)
        );

        public Builder(Activity activity) {
            this.activity = activity;
        }

        public Builder withTypedRadius(ShowCaseRadius radius) {
            this.radius = radius;
            return this;
        }

        public Builder withTypedPosition(ShowCasePosition position) {
            this.position = position;
            return this;
        }

        public Builder withDismissListener(DismissListener listener) {
            this.dismissListener = listener;
            return this;
        }

        public Builder withColor(@ColorRes int overlayColor) {
            color = overlayColor;
            return this;
        }

        @SuppressLint("InflateParams")
        public Builder withContent(String cardText) {
            this.contentView = (TextView) activity.getLayoutInflater().inflate(
                    R.layout.item_show_case_content,
                    null
            );
            this.contentText = cardText;

            return this;
        }

        public ShowCaseView build() {
            ShowCaseView view = new ShowCaseView(activity);
            view.setDismissListener(this.dismissListener);
            view.setTypedRadius(this.radius);
            view.setTypedPosition(this.position);
            view.setOverlayColor(this.color);

            if (this.contentView != null && contentText != null) {
                view.setContent(this.contentView, this.contentText);
            }

            return view;
        }
    }
}
