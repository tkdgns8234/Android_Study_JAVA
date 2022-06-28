package com.test.googlelogin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();
        String id = intent.getStringExtra(GoogleAccount.ID);
        String imgUrl = intent.getStringExtra(GoogleAccount.IMG_PROFILE);

        ((TextView)findViewById(R.id.tv_profile)).setText(id);
        ImageView photo = findViewById(R.id.iv_profile);
        Glide.with(this).load(imgUrl).into(photo);
    }
}
