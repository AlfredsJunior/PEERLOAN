package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by getab on 3/20/2018.
 */

@Entity
public class AdminUser {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "admin_id")
    public long adminId;
    @ColumnInfo(name = "admin_name")
    public String adminName;
    @ColumnInfo(name = "admin_password")
    public String adminPassword;

}
