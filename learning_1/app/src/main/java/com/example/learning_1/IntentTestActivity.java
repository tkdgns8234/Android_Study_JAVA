package com.example.learning_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentTestActivity extends AppCompatActivity {
    static final int INTENT_RESULT = 155;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);
        findViewById(R.id.btn_intentTest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(INTENT_RESULT);
                finish();
            }
        });
    }
}