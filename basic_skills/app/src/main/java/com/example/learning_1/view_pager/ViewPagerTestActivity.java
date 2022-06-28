package com.example.learning_1.view_pager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.learning_1.R;

public class ViewPagerTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_test);
        initViewPager();
    }

    private void initViewPager() {
        TextViewPageAdapter pageAdapter = new TextViewPageAdapter(this.getApplicationContext());
        ViewPager viewPager = findViewById(R.id.vp_vpTest);
        viewPager.setAdapter(pageAdapter);
    }
}