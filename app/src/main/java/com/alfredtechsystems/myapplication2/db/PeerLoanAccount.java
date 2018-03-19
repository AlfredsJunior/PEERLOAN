package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.UUID;

/**
 * Created by getab on 3/20/2018.
 */

@Entity
public class PeerLoanAccount {
    @PrimaryKey
    UUID accountId;
    double balance;

}
