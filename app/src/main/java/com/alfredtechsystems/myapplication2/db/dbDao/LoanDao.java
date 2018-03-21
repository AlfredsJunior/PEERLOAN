package com.alfredtechsystems.myapplication2.db.dbDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alfredtechsystems.myapplication2.db.dbModel.Investment;
import com.alfredtechsystems.myapplication2.db.dbModel.Loan;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Job on 3/20/2018.
 */

public interface LoanDao {
    @Query("select * from Loan")
    LiveData<List<Loan>> loadAllLoans();

    @Query("SELECT * FROM Investment" +
            "INNER JOIN user.user_id  = = Investment.fk_user_id" +
            "WHERE User.user_name == :username ")
    LiveData<List<Investment>> loadInvestMentsByUser(String username);

    @Update(onConflict = REPLACE)
    void updateMyInvestment(Investment investment);

    @Insert(onConflict = IGNORE)
    void insertMyInvestment(Investment investment);

    @Delete
    void deleteMyInvestment(Investment investment);
}
