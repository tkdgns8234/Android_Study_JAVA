package com.example.learning_1.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learning_1.R;

public class DisplayMessageActivity extends AppCompatActivity {
    static final int TEST_REC_CODE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = (TextView) findViewById(R.id.tv_displayMessage);
        textView.setText(message);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Button button = (Button)findViewById(R.id.btn_displayMessage);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivityForResult(new Intent(DisplayMessageActivity.this,
                        IntentTestActivity.class), TEST_REC_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEST_REC_CODE) {
            Toast.makeText(getApplicationContext(),
                    "rs code: " + requestCode, Toast.LENGTH_LONG).show();
        }
    }
}