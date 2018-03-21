package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by getab on 3/20/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "user_id",
                childColumns = "fk_user_id")})
public class Investment {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "invest_id")
    public String investId;
    @ColumnInfo(name = "fk_user_id")
    public String userId;
    public double investAmount;
    @TypeConverters(DateConverter.class)
    public Date invesmentDate;
}
