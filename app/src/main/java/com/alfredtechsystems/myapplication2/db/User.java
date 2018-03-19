package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by getab on 3/20/2018.
 */

@Entity
public class User {
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    String userId;
    @ColumnInfo(name = "user_name")
    String userName;
    @ColumnInfo(name = "user_password")
    String userPassword;
    String userEmail;
    int gender;
    long phoneNumber;
    boolean isAdmin;
}
