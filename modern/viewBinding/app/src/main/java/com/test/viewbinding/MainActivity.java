package com.test.viewbinding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.test.viewbinding.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding 객체에 inflate
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());

        // getRoot = 최상위 뷰 그룹을 의미
        View view = mainBinding.getRoot();
        setContentView(view);

        mainBinding.btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainBinding.tvTest.setText("Activity binding test complete\n");
            }
        });
        
        //fragment show
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, new FragmentTest());
        ft.commit();
    }
}