package com.alekseyb.splines;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButton(View view) {
        TextView text = findViewById(R.id.text);
        double x [] = { 0.0,  1.0,  1.2,  4.0,  4.2, 5.0, 6.0};
        double y [] = { 5.0,  2.0,  6.0,  6.0,  3.0, 4.0, 4.5};
        Spline3D spline = new Spline3D (x, y);
        text.setText(Double.toString(spline.s3(0.5)));
    }
}
