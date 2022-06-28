package com.example.learning_1.navigationmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learning_1.R;

public class NavigationMenuActivity extends AppCompatActivity {
    public static String TAG = "NavigationMenuActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_menu);
        initViews();
    }

    private void initViews() {
        DrawerLayout drawerLayout = findViewById(R.id.layout_nvg);
        View drawerView = findViewById(R.id.nvg_menu);

        Button btnOpen = findViewById(R.id.btn_nvg);
        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(drawerView);
            }
        });

        Button btn_close = findViewById(R.id.btn_nvg_close);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawers();
            }
        });

        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }
}