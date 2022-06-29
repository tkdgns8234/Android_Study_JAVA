package com.test.roomdatabase;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    //UserDao 인터페이스를 return 하는 userDao 라는 abstarct 메서드
    public abstract UserDao userDao();
}
