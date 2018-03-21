package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.Date;


/**
 * Created by Alfred on 3/20/2018.
 */

@Entity

public class MoneyTransaction {
    @PrimaryKey
    @NonNull
    public String txnId;
    public String txnType;
    public double amount;
    @TypeConverters(DateConverter.class)
    public Date txnDate;

}
