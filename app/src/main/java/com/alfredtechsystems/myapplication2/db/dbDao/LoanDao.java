package com.alfredtechsystems.myapplication2.db.dbDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alfredtechsystems.myapplication2.db.dbModel.Loan;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Job on 3/20/2018.
 */

@Dao
public interface LoanDao {
    @Query("select * from Loan")
    LiveData<List<Loan>> loadAllLoans();

    @Query("SELECT * FROM Loan " +
            " INNER JOIN user ON user_id == fk_user_id " +
            " WHERE user_id == :username")
    LiveData<List<Loan>> loadLoanssByUser(String username);

    @Update(onConflict = REPLACE)
    void updateLoan(Loan loan);

    @Insert(onConflict = IGNORE)
    void insertLoan(Loan loan);

    @Delete
    void deleteMyLoan(Loan loan);
}
