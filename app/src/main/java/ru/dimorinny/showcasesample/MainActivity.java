package ru.dimorinny.showcasesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ru.dimorinny.showcasecard.ShowCaseView;
import ru.dimorinny.showcasecard.position.BottomLeft;
import ru.dimorinny.showcasecard.position.BottomRight;
import ru.dimorinny.showcasecard.position.ShowCasePosition;
import ru.dimorinny.showcasecard.position.TopLeft;
import ru.dimorinny.showcasecard.position.TopLeftToolbar;
import ru.dimorinny.showcasecard.position.TopRight;
import ru.dimorinny.showcasecard.position.TopRightToolbar;
import ru.dimorinny.showcasecard.position.ViewPosition;
import ru.dimorinny.showcasecard.radius.Radius;
import ru.dimorinny.showcasecard.radius.ShowCaseRadius;

public class MainActivity extends AppCompatActivity {

    private Button topLeft;
    private Button topRight;
    private Button bottomLeft;
    private Button bottomRight;
    private Button topLeftToolbar;
    private Button topRightToolbar;
    private Button viewPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initButtons();

        topLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new TopLeft());
            }
        });

        topRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new TopRight());
            }
        });

        bottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new BottomLeft());
            }
        });

        bottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new BottomRight());
            }
        });

        topLeftToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new TopLeftToolbar());
            }
        });

        topRightToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new TopRightToolbar());
            }
        });

        viewPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTipWithPosition(new ViewPosition(
                        viewPosition
                ));
            }
        });
    }

    private void initButtons() {
        topLeft = (Button) findViewById(R.id.top_left);
        topRight = (Button) findViewById(R.id.top_right);
        bottomLeft = (Button) findViewById(R.id.bottom_left);
        bottomRight = (Button) findViewById(R.id.bottom_right);
        topLeftToolbar = (Button) findViewById(R.id.top_left_toolbar);
        topRightToolbar = (Button) findViewById(R.id.top_right_toolbar);
        viewPosition = (Button) findViewById(R.id.view_position);
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
