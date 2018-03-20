package com.alfredtechsystems.myapplication2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.alfredtechsystems.myapplication2.db.AppDatabase;
import com.alfredtechsystems.myapplication2.db.DatabaseInitilizer;
import com.alfredtechsystems.myapplication2.db.dbModel.AdminUser;
import com.alfredtechsystems.myapplication2.db.dbModel.User;

import java.util.List;

/**
 * Created by Job on 3/20/2018.
 */

public class MainActivityViewModel extends AndroidViewModel {

    private AppDatabase db;
    private static LiveData<List<User>> mUsers;
    private static LiveData<List<AdminUser>> mAdminUsers;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        db = AppDatabase.getPeerLoanDatabase(this.getApplication());
        mUsers = db.userModel().loadAllUsers();

        //initializes our users & admins
        PopulateAsync populateAsync = new PopulateAsync(db);
        populateAsync.execute();
    }

    public LiveData<List<User>> getmUsers() {
        return mUsers;
    }

    public LiveData<List<AdminUser>> getmAdminUsers() {
        return mAdminUsers;
    }

    private static class PopulateAsync extends AsyncTask<Void,Void,Void>{
        private AppDatabase mDb;

        public PopulateAsync(AppDatabase mDb) {
            this.mDb = mDb;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //default init
            DatabaseInitilizer.PopulateInitUsers(mDb);

            mUsers = mDb.userModel().loadAllUsers();
            mAdminUsers = mDb.adminUserModel().loadAllAdmins();
            return null;
        }
    }
}
