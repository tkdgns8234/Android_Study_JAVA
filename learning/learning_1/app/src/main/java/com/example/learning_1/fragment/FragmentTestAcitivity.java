package com.example.learning_1.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.example.learning_1.R;
import com.example.learning_1.fragment.frg.Fragment1;
import com.example.learning_1.fragment.frg.Fragment2;
import com.example.learning_1.fragment.frg.Fragment3;

public class FragmentTestAcitivity extends AppCompatActivity {
    /* 일반적인 replace 방법이 아닌
    * fragment 재사용 및 back 버튼을 눌렀을때 프래그먼트의 back stack 제어
    * */
    
    Fragment mF1, mF2, mF3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test_acitivity);
        initViews();
    }

    private void initViews() {
        Button button = findViewById(R.id.btn_fragment_1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(0);
            }
        });

        Button button1 = findViewById(R.id.btn_fragment_2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(1);
            }
        });

        Button button2 = findViewById(R.id.btn_fragment_3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFragment(2);
            }
        });
    }

    private void showFragment(int page) {
        FragmentManager manager = getSupportFragmentManager();
        boolean isInBackStack = false;
        String[] FragmentTags = {"fr1", "fr2", "fr3"};

        switch (page) {
            case 0:
                if (mF1 == null) mF1 = new Fragment1();

                for (Fragment fr : manager.getFragments()) {
                    if (fr instanceof Fragment1) {
                        isInBackStack = true;
                    }
                }
                changeFragment(isInBackStack, mF1, FragmentTags[0]);
                break;
            case 1:
                if (mF2 == null) mF2 = new Fragment2();

                for (Fragment fr : manager.getFragments()) {
                    if (fr instanceof Fragment2) {
                        isInBackStack = true;
                    }
                }
                changeFragment(isInBackStack, mF2, FragmentTags[1]);
                break;
            case 2:
                if (mF3 == null) mF3 = new Fragment3();

                for (Fragment fr : manager.getFragments()) {
                    if (fr instanceof Fragment3) {
                        isInBackStack = true;
                    }
                }
                changeFragment(isInBackStack, mF3, FragmentTags[2]);
                break;
        }
    }

    private void changeFragment(boolean isInBackStack, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (isInBackStack) {
            for (Fragment f : getSupportFragmentManager().getFragments()) {
                transaction.hide(f);
            }
            transaction.show(fragment);
        }
        else {
            transaction.add(R.id.frameLayout, fragment, tag);
            transaction.addToBackStack(tag);
        }
        transaction.commit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        FragmentManager mgr = getSupportFragmentManager();
        
        int backStackCnt = mgr.getBackStackEntryCount();
        if (keyCode == KeyEvent.KEYCODE_BACK && backStackCnt > 1) {
            // 다음 fragment를 tag 값으로 찾은 후 show() 한다.
            String tag = mgr.getBackStackEntryAt(backStackCnt - 2).getName();
            Fragment fr = mgr.findFragmentByTag(tag);
            mgr.beginTransaction().show(fr).commit();

            // 현재 프래그먼트의 백스택 맨 위에있는 프래그먼트 pop
            mgr.popBackStack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}