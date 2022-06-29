package com.test.kakaologin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.test.kakaologin.databinding.ActivitySubBinding;

public class subActivity extends AppCompatActivity {
    private ActivitySubBinding mSubBinding;
    private String mNickName, mEmail, mProfileImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubBinding = ActivitySubBinding.inflate(getLayoutInflater());
        setContentView(mSubBinding.getRoot());

        Intent intent = getIntent();
        mEmail = intent.getStringExtra(User.EMAIL);
        mNickName = intent.getStringExtra(User.NAME);
        mProfileImg = intent.getStringExtra(User.PROFILE_IMG);

        // email set
        mSubBinding.tvEmail.setText(mEmail);
        // nickname set
        mSubBinding.tvNickname.setText(mNickName);
        // profile img set
        Glide.with(this).load(mProfileImg).into(mSubBinding.ivProfile);

        // 로그아웃 버튼
        mSubBinding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserManagement.getInstance().requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        // 로그아웃 성공 시
                        finish();
                    }
                });
            }
        });

    }
}