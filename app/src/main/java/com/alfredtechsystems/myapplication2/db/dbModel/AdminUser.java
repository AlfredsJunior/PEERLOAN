package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by getab on 3/20/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "user_id",
                childColumns = "admin_id")})
public class AdminUser {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "admin_id")
    public String adminId;
    @ColumnInfo(name = "admin_name")
    public String adminName;
    @ColumnInfo(name = "admin_password")
    public String adminPassword;

}
