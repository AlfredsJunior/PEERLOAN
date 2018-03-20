package com.alfredtechsystems.myapplication2.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.alfredtechsystems.myapplication2.db.dbDao.AdminUserDao;
import com.alfredtechsystems.myapplication2.db.dbDao.InvestmentDao;
import com.alfredtechsystems.myapplication2.db.dbDao.LoanDao;
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

@Database(entities = {User.class, AdminUser.class, Investment.class,
        Loan.class, MoneyTransaction.class, PeerLoanAccount.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    //abstract methods for our Daos
    public abstract UserDao userModel();
    public abstract AdminUserDao adminUserModel();
    public abstract LoanDao loanModel();
    public abstract InvestmentDao investmentModel();


    public static AppDatabase getPeerLoanDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "peerloandb")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyPeerLoanDb(){
        INSTANCE =null;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Since we didn't alter any table, there's nothing else to do here.
            //This illustrates migration of databases.
        }
    };
}
