package com.alfredtechsystems.myapplication2.db.dbDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.alfredtechsystems.myapplication2.db.dbModel.Investment;

import java.util.List;

/**
 * Created by getab on 3/20/2018.
 */

@Dao
public interface InvestmentDao {

    @Query("select * from Investment")
    LiveData<List<Investment>> loadAllInvestMents();

    @Query("SELECT * FROM Investment" +
            "INNER JOIN user.user_id = Investment.fk_user_id" +
            "WHERE User.user_name = :username ")
    LiveData<List<Investment>> loadInvestMentsByUser(String username);
}
