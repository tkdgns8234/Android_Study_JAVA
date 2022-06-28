package com.example.learning_1.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.learning_1.R;

import java.util.ArrayList;

public class RecyclerViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_test);
        setViews();
    }
    
    private void setViews() {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            list.add("test " + i);
        }
        RecyclerView rcView = findViewById(R.id.rcView);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        ViewAdapter adapter = new ViewAdapter(list);
        rcView.setAdapter(adapter);
    }
}