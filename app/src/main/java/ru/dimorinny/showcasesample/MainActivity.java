package ru.dimorinny.showcasesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.dimorinny.showcasecard.ShowCasePosition;
import ru.dimorinny.showcasecard.ShowCaseRadius;
import ru.dimorinny.showcasecard.ShowCaseView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ShowCaseView.Builder(this)
                .withTypedPosition(
                        new ShowCasePosition.ViewPosition(
                                findViewById(R.id.hello_label)
                        )
                )
                .withTypedRadius(
                        new ShowCaseRadius.ViewRadius(
                                findViewById(R.id.hello_label),
                                .7F
                        )
                )
                .withContent(
                        "This is hello world!"
                )
                .build()
                .show(this);
    }
}
