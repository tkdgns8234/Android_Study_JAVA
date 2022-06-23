package com.example.learning_1.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.learning_1.R;

public class WebViewTestActivity extends AppCompatActivity {
    static final String URL = "https://www.naver.com";
    static final String TAG = "WebViewTestActivity";
    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        initViews();
    }

    private void initViews() {
        mWebView = findViewById(R.id.webView);
        // 자바 스크립트 허용
        mWebView.getSettings().setJavaScriptEnabled(true);
        // 웹 뷰 실행
        mWebView.loadUrl(URL);

        // 크롬에 맞춰 쾌적한 환경 조성
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Log.d(TAG, "onKeyDown + " + keyCode);
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            Log.d(TAG, "goBack");
            mWebView.goBack();
            // 아래 return 문 추가해야 정상동작
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}