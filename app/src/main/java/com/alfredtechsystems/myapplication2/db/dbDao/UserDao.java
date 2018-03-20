package com.alfredtechsystems.myapplication2.db.dbDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alfredtechsystems.myapplication2.db.dbModel.User;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by getab on 3/20/2018.
 */

@Dao
public interface UserDao {
    @Query("select * from user")
    LiveData<List<User>> loadAllUsers();

    @Query("select * from user where user_id = :userid")
    LiveData<User> loadUserById(long userid);

    @Query("select * from user where user_id = :userid and user_name = :username")
    LiveData<User> findUserByNameAndLastName(long userid, String username);

    @Insert(onConflict = REPLACE)
    void insertUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM User WHERE user_id = :userid")
    void deleteUser(long userid);

    @Query("DELETE FROM User")
    void deleteAllUsers();
}
