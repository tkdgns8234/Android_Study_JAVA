package com.example.learning_1.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learning_1.R;

public class MainActivity extends AppCompatActivity {
    // 패키지 이름을 접두사로 사용하는것이 좋음, 상수가 다른 앱과 상호작용 시 겹치지 않음
    public static final String EXTRA_MESSAGE = "com.example.learning_1.MESSAGE";

    static final String STATE_NUM = "player_num";
    private int mCurrentVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // layout xml 파일의 onClick 키워드를 활용한 callback 등록
    // public으로 선언해야하기에 이 방식은 당연히 지양할거같다. (일단 연습이니 해보자)
    public void sendMessage(View view) {
        Toast.makeText(this, ((Button)view).getText(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText)findViewById(R.id.et_main);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        // super 클래스를 통해 View 객체 상태를 저장
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_NUM ,mCurrentVal);
    }

    @Override
    public void onRestoreInstanceState(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        int num = savedInstanceState.getInt(STATE_NUM);
        System.out.println(num);
    }
}