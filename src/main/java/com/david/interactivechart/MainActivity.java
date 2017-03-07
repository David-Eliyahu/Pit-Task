package com.david.interactivechart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.david.interactivechart.charting.Pit;

public class MainActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Pit pit = (Pit) findViewById(R.id.pit);

        findViewById(R.id.addPoint).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pit.addPoint();
            }
        });
    }
}
