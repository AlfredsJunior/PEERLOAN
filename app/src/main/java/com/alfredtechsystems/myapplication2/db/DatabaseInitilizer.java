package com.alfredtechsystems.myapplication2.db;

import com.alfredtechsystems.myapplication2.db.dbModel.AdminUser;
import com.alfredtechsystems.myapplication2.db.dbModel.User;

/**
 * Created by Job on Tuesday : 3/20/2018.
 */

public class DatabaseInitilizer {
    public static void PopulateInitUsers(final AppDatabase db){
        db.userModel().deleteAllUsers();
        db.adminUserModel().deleteAllAdmin();

        User user1 = addUser(db);

        if (user1.isAdmin){
            AdminUser adminUser = new AdminUser();
            adminUser.adminId = user1.userId;
            adminUser.adminName = user1.userName;
            adminUser.adminPassword = user1.userPassword;
            db.adminUserModel().insertAdmin(adminUser);
        }
    }

    private static User addUser(AppDatabase db){
        User user = new User();
        user.userName="Job";
        user.userId=34311813;
        user.gender=1;
        user.isAdmin=true;
        user.phoneNumber=254708440184L;
        user.userPassword="1234";
        db.userModel().insertUser(user);

        return  user;
    }
}
