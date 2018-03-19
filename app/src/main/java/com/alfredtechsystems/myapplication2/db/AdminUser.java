package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by getab on 3/20/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "user_id",
                childColumns = "admin_id")})
public class AdminUser {
    @PrimaryKey
    @ColumnInfo(name = "admin_id")
    String adminId;
    @ColumnInfo(name = "admin_name")
    String adminName;
    @ColumnInfo(name = "admin_password")
    String adminPassword;

}
