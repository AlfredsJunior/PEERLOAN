package com.alfredtechsystems.myapplication2.db.dbDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.alfredtechsystems.myapplication2.db.dbModel.Investment;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Alfred on 3/20/2018.
 */

@Dao
public interface InvestmentDao {

    @Query("select * from Investment")
    LiveData<List<Investment>> loadAllInvestments();

    @Query(value = "SELECT * FROM Investment," +
            "INNERJOIN User.user_id==Investment.fk_user_id" +
            "WHERE User.user_name == :username ")
    LiveData<List<Investment>> loadInvestmentsByUser(String username);

    @Update(onConflict = REPLACE)
    void updateMyInvestment(Investment investment);

    @Insert(onConflict = IGNORE)
    void insertMyInvestment(Investment investment);

    @Delete
    void deleteMyInvestment(Investment investment);
}
