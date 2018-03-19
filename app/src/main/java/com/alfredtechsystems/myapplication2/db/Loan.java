package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

/**
 * Created by getab on 3/20/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "user_id",
                childColumns = "fk_user_id")})
@TypeConverters(DateConverter.class)
public class Loan {
    @PrimaryKey
    @ColumnInfo(name = "loan_id")
    String loanId;
    @ColumnInfo(name = "fk_user_id")
    String userId;
    double loanAmount;
}
