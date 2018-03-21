package com.alfredtechsystems.myapplication2.db.dbModel;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Alfred on 3/20/2018.
 */

@Entity
public class PeerLoanAccount {
    @PrimaryKey
    @NonNull
    public int accountId;
    public double balance;

}
