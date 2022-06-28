package com.example.learning_1.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.learning_1.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ListViewTestActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> mItems = new ArrayList<>();
    private final String FILE_NAME = "file.txt";
    private ArrayAdapter mArrayAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_test);
        initViews();
    }

    private void initViews() {
        Button btnAdd = findViewById(R.id.btn_lvTest_add);
        btnAdd.setOnClickListener(this);
        Button btnDelete = findViewById(R.id.btn_lvTest_delete);
        btnDelete.setOnClickListener(this);

        EditText editText = findViewById(R.id.edt_lvTest);
        editText.addTextChangedListener(new TextWatcher());

        // listView를 위한 item load
        loadItemsFromFile();
        //ListView 초기화
        mListView = findViewById(R.id.lv_lvTest);
        mArrayAdapter = new ArrayAdapter(
                this, android.R.layout.simple_list_item_single_choice, mItems);
        mListView.setAdapter(mArrayAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_lvTest_add) {
            EditText editText = findViewById(R.id.edt_lvTest);
            String text = editText.getText().toString();
            mItems.add(text);
            mArrayAdapter.notifyDataSetChanged();
            editText.setText("");

        } else if (id == R.id.btn_lvTest_delete) {
            int pos = mListView.getCheckedItemPosition();
            if (pos != AdapterView.INVALID_POSITION) {
                mItems.remove(pos);
                mArrayAdapter.notifyDataSetChanged();
                mListView.clearChoices();
            }
        }
        saveItemsToFile();
    }

    private class TextWatcher implements android.text.TextWatcher {
        @Override
        public void afterTextChanged(Editable s) {
            // add 버튼 활/비활성화
            Button btnAdd = findViewById(R.id.btn_lvTest_add);
            if (s.toString().length() > 0) {
                btnAdd.setEnabled(true);
            } else {
                btnAdd.setEnabled(false);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }
    }

    private void saveItemsToFile() {
        // 내부 저장소 사용
        File file = new File(getFilesDir(), FILE_NAME);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter br = new BufferedWriter(fw);
            for (String item : mItems) {
                // listView의 item 저장
                br.write(item + '\n');
            }
            br.flush();

            fw.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadItemsFromFile() {
        File file = new File(getFilesDir(), FILE_NAME);
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                mItems.add(line);
            }

            fr.close();
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}