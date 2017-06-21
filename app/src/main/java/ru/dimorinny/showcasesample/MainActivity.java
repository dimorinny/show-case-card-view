package ru.dimorinny.showcasesample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ru.dimorinny.showcasecard.ShowCaseViewJava;
import ru.dimorinny.showcasecard.position.TopLeftToolbar;
import ru.dimorinny.showcasecard.radius.ViewRadius;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ShowCaseViewJava.Builder(MainActivity.this)
//                .withTypedPosition(new ShowCasePosition.BottomRight())
//                .withTypedPosition(new ShowCasePosition.TopRightToolbar())
//                .withTypedPosition(
//                        new ViewPosition(
//                                findViewById(R.id.hello_label)
//                        )
//                )
                .withTypedPosition(new TopLeftToolbar())
                .withTypedRadius(
                        new ViewRadius(
                                findViewById(R.id.hello_label),
                                .7F
                        )
                )
                .withContent(
                        "This is hello world!"
                )
                .build()
                .show(MainActivity.this);
    }
}
