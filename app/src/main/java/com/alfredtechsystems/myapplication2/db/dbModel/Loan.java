package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Alfred on 3/20/2018.
 */

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class,
                parentColumns = "user_id",
                childColumns = "fk_user_id")})
public class Loan {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "loan_id")
    public String loanId;
    @ColumnInfo(name = "fk_user_id")
    public String userId;
    public double loanAmount;
    @TypeConverters(DateConverter.class)
    public Date loanDate;
}
