package com.alfredtechsystems.myapplication2.db.dbDao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.alfredtechsystems.myapplication2.db.dbModel.AdminUser;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

/**
 * Created by Job on 3/20/2018.
 */

@Dao
public interface AdminUserDao {
    @Query("select * from adminuser")
    LiveData<List<AdminUser>> loadAllAdmins();

    @Query("select * from adminuser where admin_id = :adminid")
    LiveData<AdminUser> loadAdminById(int adminid);

    @Insert(onConflict = IGNORE)
    void insertAdmin(AdminUser adminuser);

    @Query("DELETE FROM AdminUser " +
            "WHERE admin_id = :adminid")
    void deleteAdmin(int adminid);

    @Query("DELETE FROM AdminUser ")
    void deleteAllAdmin();

    @Delete
    void deleteAdmin(AdminUser adminuser);

}

