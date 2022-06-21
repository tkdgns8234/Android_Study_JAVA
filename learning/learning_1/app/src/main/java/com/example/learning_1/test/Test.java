package com.example.learning_1.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.learning_1.R;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        ImageView v1 = findViewById(R.id.v1);
        ImageView v2 = findViewById(R.id.v2);
        Button btn = findViewById(R.id.btn_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v1.getVisibility() == View.INVISIBLE) {
                    v1.setVisibility(View.VISIBLE);
                    v2.setVisibility(View.INVISIBLE);
                }
                else {
                    v1.setVisibility(View.INVISIBLE);
                    v2.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}