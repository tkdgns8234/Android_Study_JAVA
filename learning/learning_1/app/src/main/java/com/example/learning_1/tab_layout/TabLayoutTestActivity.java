package com.example.learning_1.tab_layout;

import androidx.appcompat.app.AppCompatActivity;

import android.location.GnssAntennaInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.learning_1.R;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutTestActivity extends AppCompatActivity {
    static final String TAG = "TabLayoutTestActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_test);
        setViews();
    }

    private void setViews() {
        TabLayout tabLayout = findViewById(R.id.tabLayout_tlTest);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tlTest_tabC));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                changeTabPage(pos);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void changeTabPage(int pos) {
        ImageView iv1 = (ImageView) findViewById(R.id.iv_tlTest_1);
        ImageView iv2 = (ImageView) findViewById(R.id.iv_tlTest_2);
        ImageView iv3 = (ImageView) findViewById(R.id.iv_tlTest_3);
        switch (pos) {
            case 0:
                iv1.setVisibility(View.VISIBLE);
                iv2.setVisibility(View.INVISIBLE);
                iv3.setVisibility(View.INVISIBLE);
                break;
            case 1:
                iv1.setVisibility(View.INVISIBLE);
                iv2.setVisibility(View.VISIBLE);
                iv3.setVisibility(View.INVISIBLE);
                break;
            case 2:
                iv1.setVisibility(View.INVISIBLE);
                iv2.setVisibility(View.INVISIBLE);
                iv3.setVisibility(View.VISIBLE);
                break;
        }
    }

}