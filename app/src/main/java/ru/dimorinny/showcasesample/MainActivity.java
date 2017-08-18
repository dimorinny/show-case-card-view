package ru.dimorinny.showcasesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.BottomLeft;
import ru.dimorinny.showcasecard.position.BottomRight;
import ru.dimorinny.showcasecard.position.Center;
import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.position.TopLeft;
import ru.dimorinny.showcasecard.position.TopLeftToolbar;
import ru.dimorinny.showcasecard.position.TopRight;
import ru.dimorinny.showcasecard.position.TopRightToolbar;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;
import ru.dimorinny.showcasecard.radius.ShowCaseRadius;
import ru.dimorinny.showcasecard.step.ShowCaseStepDisplayer;
import ru.dimorinny.showcasecard.step.ShowCaseStep;

public class MainActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private View dummyViewToScrollTo;

    private Button topLeft;
    private Button topRight;
    private Button bottomLeft;
    private Button bottomRight;
    private Button topLeftToolbar;
    private Button topRightToolbar;
    private Button viewPosition;
    private Button listOfSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = findViewById(R.id.activity_main);
        dummyViewToScrollTo = findViewById(R.id.dummy_view_to_scroll_to);

        initButtons();

        topLeft.setOnClickListener(v -> showTipWithPosition(new TopLeft()));
        topRight.setOnClickListener(v -> showTipWithPosition(new TopRight()));
        bottomLeft.setOnClickListener(v -> showTipWithPosition(new BottomLeft()));
        bottomRight.setOnClickListener(v -> showTipWithPosition(new BottomRight()));
        topLeftToolbar.setOnClickListener(v -> showTipWithPosition(new TopLeftToolbar()));
        topRightToolbar.setOnClickListener(v -> showTipWithPosition(new TopRightToolbar()));
        viewPosition.setOnClickListener(v -> showTipWithPosition(new ViewPosition(
                viewPosition
        )));
        listOfSteps.setOnClickListener(v -> displayListOfSteps());
    }

    private void displayListOfSteps() {

        new ShowCaseStepDisplayer.Builder(this)
                .withScrollView(scrollView)
                .addStep(new ShowCaseStep(new Center(), "This is the center of the screen. Tap anywhere to continue."))
                .addStep(new ShowCaseStep(listOfSteps, "This is the button you just clicked."))
                .addStep(new ShowCaseStep(dummyViewToScrollTo, "A dummy item to auto-scroll to.",
                        true))
                .addStep(new ShowCaseStep(topLeft, "We end our showcase at the top button.", true))
                .build().start();
    }

    private void initButtons() {
        topLeft = findViewById(R.id.top_left);
        topRight = findViewById(R.id.top_right);
        bottomLeft = findViewById(R.id.bottom_left);
        bottomRight = findViewById(R.id.bottom_right);
        topLeftToolbar = findViewById(R.id.top_left_toolbar);
        topRightToolbar = findViewById(R.id.top_right_toolbar);
        viewPosition = findViewById(R.id.view_position);
        listOfSteps = findViewById(R.id.list_of_steps);
    }

    private void showTipWithPosition(ShowCasePosition position) {
        showTip(
                position,
                new Radius(186F)
        );
    }

    private void showTip(ShowCasePosition position, ShowCaseRadius radius) {
        new ShowCaseView.Builder(MainActivity.this)
                .withTypedPosition(position)
                .withTypedRadius(radius)
                .withContent(
                        "This is hello world!"
                )
                .build()
                .show(MainActivity.this);
    }
}
