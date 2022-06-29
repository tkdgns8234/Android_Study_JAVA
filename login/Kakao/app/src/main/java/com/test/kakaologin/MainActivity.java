package com.test.kakaologin;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.test.kakaologin.databinding.ActivityMainBinding;

/**
 * kakao developers 사이트에서 앱 및 hash key 등록
 */

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mMainBinding;
    private ISessionCallback mSessionCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mMainBinding.getRoot());


        mSessionCallback = new ISessionCallback() {
            @Override
            public void onSessionOpened() {
                // 로그인 요청
                UserManagement.getInstance().me(new MeV2ResponseCallback() {
                    @Override
                    public void onFailure(ErrorResult errorResult) {
                        // 로그인에 실패한 경우
                        Toast.makeText(MainActivity.this, "로그인 도중에 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSessionClosed(ErrorResult errorResult) {
                        // 세션이 닫힌경우
                        Toast.makeText(MainActivity.this, "세션이 닫혔습니다.. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onSuccess(MeV2Response result) {
                        // 로그인 성공
                        // result 변수에 kakao developers에서 설정한 user 요청 정보가 담기게 됨
                        Intent intent = new Intent(MainActivity.this, subActivity.class);
                        intent.putExtra(User.NAME, result.getKakaoAccount().getProfile().getNickname());
                        intent.putExtra(User.PROFILE_IMG, result.getKakaoAccount().getProfile().getProfileImageUrl());
                        intent.putExtra(User.EMAIL, result.getKakaoAccount().getEmail());
                        startActivity(intent);

//                        Toast.makeText(MainActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onSessionOpenFailed(KakaoException exception) {

            }
        };

        Session.getCurrentSession().addCallback(mSessionCallback);
        // 로그아웃하지 않으면 자동 로그인
        Session.getCurrentSession().checkAndImplicitOpen();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // 카카오에서 로그인 처리 후 호출됨
        if (Session.getCurrentSession().handleActivityResult(requestCode, requestCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //세션 해제
        Session.getCurrentSession().removeCallback(mSessionCallback);
    }
}