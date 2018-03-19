package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import com.alfredtechsystems.myapplication2.db.dbModel.AdminUser;
import com.alfredtechsystems.myapplication2.db.dbModel.Investment;
import com.alfredtechsystems.myapplication2.db.dbModel.Loan;
import com.alfredtechsystems.myapplication2.db.dbModel.MoneyTransaction;
import com.alfredtechsystems.myapplication2.db.dbModel.PeerLoanAccount;
import com.alfredtechsystems.myapplication2.db.dbModel.User;

/**
 * Created by getab on 3/18/2018.
 */

@Database(entities = {User.class,AdminUser.class,Investment.class,
        Loan.class,MoneyTransaction.class,PeerLoanAccount.class},version = 1)
public class AppDatabase extends RoomDatabase {
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }
}
