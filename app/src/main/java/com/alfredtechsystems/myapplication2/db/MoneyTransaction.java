package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Date;

/**
 * Created by getab on 3/20/2018.
 */

@Entity
@TypeConverters(DateConverter.class)
public class MoneyTransaction {
    @PrimaryKey
    String txnId;
    String txnType;
    double amount;
    Date txnTime;

}
