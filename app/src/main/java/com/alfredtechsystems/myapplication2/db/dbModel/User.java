package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by getab on 3/20/2018.
 */

@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    public int userId;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "user_password")
    public String userPassword;
    String userEmail;
    public int gender;
    public long phoneNumber;
    public boolean isAdmin;
}
