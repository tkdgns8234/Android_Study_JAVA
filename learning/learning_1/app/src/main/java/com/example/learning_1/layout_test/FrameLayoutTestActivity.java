package com.example.learning_1.layout_test;

import androidx.appcompat.app.AppCompatActivity;

import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.learning_1.R;

public class FrameLayoutTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame_layout_test);
        initViews();
    }

    private void initViews() {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.btn_flTest_1) {
                    changeFragment(1);
                }else if (id == R.id.btn_flTest_2) {
                    changeFragment(2);
                }else if (id == R.id.btn_flTest_3) {
                    changeFragment(3);
                }
            }
        };

        Button button1 = findViewById(R.id.btn_flTest_1);
        button1.setOnClickListener(listener);
        Button button2 = findViewById(R.id.btn_flTest_2);
        button2.setOnClickListener(listener);
        Button button3 = findViewById(R.id.btn_flTest_3);
        button3.setOnClickListener(listener);
    }

    private void changeFragment(int idx) {
        FrameLayout frame = findViewById(R.id.frameLayout_flTest);
        if (frame.getChildCount() > 0) {
            frame.removeViewAt(0);
        }

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = null;
        switch (idx) {
            case 1:
                view = inflater.inflate(R.layout.layout_fltest_1, frame, false);
                break;
            case 2:
                view = inflater.inflate(R.layout.layout_fltest_2, frame, false);
                break;
            case 3:
                view = inflater.inflate(R.layout.layout_fltest_3, frame, false);
                break;
        }

        if (view != null) {
            frame.addView(view);
        }
    }
}