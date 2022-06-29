package com.test.roomdatabase;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Entity: 룸 라이브러리에서 지원하는 어노테이션
 * 일반적인 클래스가 아니라 룸에서 지원하는 데이터 모델로 사용하기 위함
 */
@Entity
public class User {  // user에 대한 데이터 model 클래스, 데이터베이스 테이블 명
    @PrimaryKey(autoGenerate = true) // ID 관리를 용이하게 하기 위함, PK로 설정
    private int id = 0;

    private String name;

    private String age;

    private String phoneNumber;

    // 위 4개의 필드는 DB 컬럼을 의미

    // getter & setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
