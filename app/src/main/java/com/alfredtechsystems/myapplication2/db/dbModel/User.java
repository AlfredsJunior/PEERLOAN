package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Alfred on 3/20/2018.
 */

@Entity
public class User {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user_id")
    public long userId;
    @ColumnInfo(name = "user_name")
    public String userName;
    @ColumnInfo(name = "user_password")
    public String userPassword;
    @Nullable
    public String userEmail;
    @Nullable
    public int gender;
    @Nullable
    public long phoneNumber;
    @Nullable
    public boolean isAdmin;
}
