package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.support.annotation.NonNull;

import com.alfredtechsystems.myapplication2.db.dbDao.InvestmentDao;
import com.alfredtechsystems.myapplication2.db.dbDao.UserDao;
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
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    //abstract methods for our Daos
    public abstract UserDao UserModel();
    public abstract InvestmentDao InvestmentModel();

    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
            //This illustrates migration of databases.
        }
    };
}
