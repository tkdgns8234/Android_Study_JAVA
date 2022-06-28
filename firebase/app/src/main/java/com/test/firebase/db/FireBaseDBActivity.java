package com.test.firebase.db;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.test.firebase.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FireBaseDBActivity extends AppCompatActivity {

    private static final String TAG = "FireBaseDBTestActivity";
    private ArrayList<User> mUserList = new ArrayList<>();
    private ArrayList<String> mUserIdList = new ArrayList<>();
    private DatabaseReference mDatabase;
    UserAdapter mUserAdapter;

    EditText edt_id;
    EditText edt_age;
    EditText edt_name;
    RadioGroup rg_gender;

    Button btn_update_fbDB;
    Button btn_refresh_fbDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate()");
        setContentView(R.layout.activity_firebase_dbtest);

        initViews();

        //load db
        mDatabase = FirebaseDatabase.getInstance().getReference("User"); //User 테이블과 연결
        readFromDB();

        RecyclerView recyclerView = findViewById(R.id.recyclerView_fbdb);
        recyclerView.setHasFixedSize(true); // 리사이클러뷰 성능강화
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mUserAdapter = new UserAdapter(mUserList, this);
        mUserAdapter.setOnItemClickListener(onItemClickListener);
        recyclerView.setAdapter(mUserAdapter);
    }

    UserAdapter.OnItemClickListener onItemClickListener = new UserAdapter.OnItemClickListener() {
        @Override
        public void OnItemClick(View v, int viewPos) {
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(FireBaseDBActivity.this);
            builder.setTitle("데이터 삭제")
                    .setMessage("해당 데이터를 삭제하시겠습니까?")
                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteDB(mUserIdList.get(viewPos));
                            mUserList.remove(viewPos);
                            mUserIdList.remove(viewPos);
                            mUserAdapter.notifyDataSetChanged();
                            Toast.makeText(FireBaseDBActivity.this,
                                    "데이터를 삭제하였습니다.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(FireBaseDBActivity.this,
                                    "삭제를 취소했습니다.", Toast.LENGTH_SHORT).show();
                        }
                    })
            .create()
            .show();
        }
    };

    private void initViews() {
        edt_id = findViewById(R.id.edt_id);
        edt_age = findViewById(R.id.edt_age);
        edt_name = findViewById(R.id.edt_name);
        rg_gender = findViewById(R.id.rg_gender);
        btn_update_fbDB = findViewById(R.id.btn_update_fbDB);
        btn_update_fbDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDB();
                hideKeyBoard();
            }
        });

        btn_refresh_fbDB = findViewById(R.id.btn_refresh_fbDB);
        btn_refresh_fbDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readFromDB();
                hideKeyBoard();
            }
        });
    }

    private void readFromDB() {
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.e(TAG, "addListenerForSingleValueEvent()");
                mUserIdList.clear();
                mUserList.clear();
                Log.e(TAG, dataSnapshot.toString());

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    mUserList.add(user);
                    mUserIdList.add(user.getId());
                }
                mUserAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e(TAG, databaseError.getMessage());
            }
        });
    }

    private void updateDB() {
        String id, name, age, gender;
        id = edt_id.getText().toString();
        name = edt_name.getText().toString();
        age = edt_age.getText().toString();
        gender = rg_gender.getCheckedRadioButtonId() == R.id.rb_man ? "man" : "woman";

        if (!age.chars().allMatch(Character::isDigit)) { // :: 람다표현식 간결화 (함수 포인터 넘기는것과 동일)
            Toast.makeText(this, "age 에는 숫자만 입력 가능합니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        if (mUserIdList.contains(id)) {
            Toast.makeText(this, "이미 존재하는 ID 입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(id, name, Integer.parseInt(age), gender);
        Map<String, Object> map = new HashMap<>();
        map.put("/"+id+"/", user);
        mDatabase.updateChildren(map);
    }

    private void deleteDB(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("/"+userId+"/", null);
        mDatabase.updateChildren(map);
    }

    private void hideKeyBoard() {
        View focus;
        if ((focus = getCurrentFocus()) != null) {
            InputMethodManager manager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}