package com.test.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private UserDao mUserDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserDatabase database =
                Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "hoon_db")
                        .fallbackToDestructiveMigration() // 스키마(database) 버전 변경 가능
                        .allowMainThreadQueries()  // Main Thread 에서 DB에 I/O를 가능하게함
                        .build();

        mUserDao = database.userDao(); // 인터페이스 객체 할당


        //데이터 삽입
/*        User user = new User();
        user.setAge("28");
        user.setName("정상훈");
        user.setPhoneNumber("7762823");
        mUserDao.setInsertUser(user);*/


        // 데이터 갱신
/*        User user = new User();
        user.setId(1);
        user.setName("새로운 유저");
        mUserDao.setUpdateUser(user);*/


        // 데이터 삭제
/*        User user = new User();
        user.setId(1);
        mUserDao.setDeleteUser(user);*/


        // 데이터 조회
        List<User> userList = mUserDao.getUserAll();
        for (int i = 0; i < userList.size(); i++) {
            Log.e(TAG, userList.get(i).getAge() + '\n'
                    + userList.get(i).getName() + '\n'
                    + userList.get(i).getPhoneNumber() + '\n'
                    + userList.get(i).getId() + '\n');
        }
    }
}