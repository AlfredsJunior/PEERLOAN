package com.alfredtechsystems.myapplication2.dataclasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashbel on 3/8/2017.
 */

public class dbOperations {
    public static final String LOGTAG = "UNTU_MOBI_SYS";

    SQLiteOpenHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.COLUMN_FIRST_NAME,
            dbDatabaseHelper.COLUMN_LAST_NAME,
            dbDatabaseHelper.COLUMN_GENDER,
            dbDatabaseHelper.COLUMN_PH_NUM,
            dbDatabaseHelper.COLUMN_ID_NUM,
            dbDatabaseHelper.COLUMN_PSWD,
            dbDatabaseHelper.COLUMN_APP_KEY,
            dbDatabaseHelper.COLUMN_STATUS,
            dbDatabaseHelper.COLUMN_MAMBU_ID,
            dbDatabaseHelper.KEY_DATE

    };

    private static final String[] allApplicationColumns = {
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.KEY_AMOUNT,
            dbDatabaseHelper.COLUMN_TENURE,
            dbDatabaseHelper.COLUMN_SECTOR,
            dbDatabaseHelper.COLUMN_OTHER_INFO,
            dbDatabaseHelper.KEY_DATE,
            dbDatabaseHelper.COLUMN_STATUS,
            dbDatabaseHelper.COLUMN_APP_ID,
            dbDatabaseHelper.COLUMN_TYPE,
            dbDatabaseHelper.COLUMN_ADDRESS,
            dbDatabaseHelper.COLUMN_INCOME,
            dbDatabaseHelper.COLUMN_EXPENSE,
            dbDatabaseHelper.COLUMN_ASSETS,
            dbDatabaseHelper.COLUMN_COLLATERAL,
            dbDatabaseHelper.COLUMN_OPS_LENGTH

    };

    private static final String[] allLoanColumns ={
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.KEY_LOAN_BAL,
            dbDatabaseHelper.KEY_INT_RATE,
            dbDatabaseHelper.KEY_PR_PAID,
            dbDatabaseHelper.COLUMN_TENURE,
            dbDatabaseHelper.KEY_INT_PAID,
            dbDatabaseHelper.KEY_FEE_PAID,
            dbDatabaseHelper.KEY_LOAN_AMNT,
            dbDatabaseHelper.KEY_ACC_NUM,
            dbDatabaseHelper.KEY_SET_ACC,
            dbDatabaseHelper.KEY_ACC_STATE,
            dbDatabaseHelper.KEY_AMT_DUE,
            dbDatabaseHelper.KEY_DATE
    };

    private static final String[] allTransactionColumns ={
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.KEY_DATE,
            dbDatabaseHelper.KEY_TYPE,
            dbDatabaseHelper.KEY_AMT,
            dbDatabaseHelper.KEY_BAL,
            dbDatabaseHelper.KEY_ACC_NUM,
            dbDatabaseHelper.TRANS_ID
    };

    private static final String[] allFeedbackColumns = {
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.KEY_FD_DETAILS,
            dbDatabaseHelper.COLUMN_STATUS,
            dbDatabaseHelper.KEY_DATE,
            dbDatabaseHelper.KEY_FD_TITLE
    };

    private static final String[] allSecurityColumns = {
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.KEY_SEC_DETAILS,
            dbDatabaseHelper.KEY_SEC_DATE,
            dbDatabaseHelper.COLUMN_STATUS,
            dbDatabaseHelper.KEY_DATE,
            dbDatabaseHelper.KEY_SEC_TYPE
    };

    private static final String[] allScheduleColumns ={
            dbDatabaseHelper.KEY_ID,
            dbDatabaseHelper.KEY_AMT_DUE,
            dbDatabaseHelper.KEY_AMT_PAID,
            dbDatabaseHelper.KEY_DATE,
            dbDatabaseHelper.KEY_ACC_NUM,
            dbDatabaseHelper.KEY_ACC_STATE
    };

    public dbOperations(Context context) {
        dbhandler = new dbDatabaseHelper(context);
    }

    public void open() {
        database = dbhandler.getWritableDatabase();


    }

    public void close() {
        dbhandler.close();

    }

    //---------------------------USERS ----------------------------------------------------------//
    public dbUsers addUsers(dbUsers dbDbUsers) {

        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.COLUMN_FIRST_NAME, dbDbUsers.getFirstname());
        values.put(dbDatabaseHelper.COLUMN_LAST_NAME, dbDbUsers.getLastname());
        values.put(dbDatabaseHelper.COLUMN_GENDER, dbDbUsers.getGender());
        values.put(dbDatabaseHelper.COLUMN_PH_NUM, dbDbUsers.getPhNum());
        values.put(dbDatabaseHelper.COLUMN_ID_NUM, dbDbUsers.getIdNum());
        values.put(dbDatabaseHelper.COLUMN_PSWD, dbDbUsers.getPswd());
        values.put(dbDatabaseHelper.COLUMN_STATUS, dbDbUsers.getSent());
        values.put(dbDatabaseHelper.COLUMN_APP_KEY, dbDbUsers.getApp_id());
        values.put(dbDatabaseHelper.COLUMN_MAMBU_ID,dbDbUsers.getMambu_id());
        values.put(dbDatabaseHelper.KEY_DATE, dbDbUsers.getCreate_date().toString());
        long insertid = database.insert(dbDatabaseHelper.TABLE_USERS, null, values);
        dbDbUsers.setUsrId(insertid);
        return dbDbUsers;

    }

    // Getting single dbUser
    public dbUsers getUsers(long id) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_USERS, allColumns, dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        dbUsers e = new dbUsers(Long.parseLong(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7),cursor.getString(8), cursor.getString(9), cursor.getString(10));
        // return dbUser
        return e;
    }

    public Boolean loginUser(String pswd) {
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_USERS, null, dbDatabaseHelper.COLUMN_PSWD + "=?", new String[]{pswd}, null, null, null);
        if (cursor != null) {
            int cursorCount = cursor.getCount();
            cursor.close();

            if (cursorCount > 0) {
                return true;
            }
        }
        return false;
    }

    public List<dbUsers> getAllUsers() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_USERS, allColumns, null, null, null, null, null);

        List<dbUsers> dbUsers = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                com.untu.fis.app.untumobile.DataClasses.dbUsers dbDbUsers1 = new dbUsers();
                dbDbUsers1.setUsrId(cursor.getLong(cursor.getColumnIndex(dbDatabaseHelper.KEY_ID)));
                dbDbUsers1.setFirstname(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_FIRST_NAME)));
                dbDbUsers1.setLastname(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_LAST_NAME)));
                dbDbUsers1.setGender(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_GENDER)));
                dbDbUsers1.setPhNum(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_PH_NUM)));
                dbDbUsers1.setIdNum(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_ID_NUM)));
                dbDbUsers1.setApp_id(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_APP_KEY)));
                dbDbUsers1.setSent(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_STATUS)));
                dbDbUsers1.setCreate_date(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE)));
                dbUsers.add(dbDbUsers1);
            }
        }
        // return All dbUserss
        return dbUsers;
    }

    public boolean userCount() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_USERS, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            return false;
        }
        return true;
    }

    // Updating dbDbUsers
    public int updateUsers(dbUsers dbDbUsers) {

        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.COLUMN_FIRST_NAME, dbDbUsers.getFirstname());
        values.put(dbDatabaseHelper.COLUMN_LAST_NAME, dbDbUsers.getLastname());
        values.put(dbDatabaseHelper.COLUMN_GENDER, dbDbUsers.getGender());
        values.put(dbDatabaseHelper.COLUMN_PH_NUM, dbDbUsers.getPhNum());
        values.put(dbDatabaseHelper.COLUMN_ID_NUM, dbDbUsers.getIdNum());
        values.put(dbDatabaseHelper.COLUMN_APP_KEY, dbDbUsers.getIdNum());
        values.put(dbDatabaseHelper.COLUMN_STATUS, dbDbUsers.getIdNum());

        // updating row
        return database.update(dbDatabaseHelper.TABLE_USERS, values,
                dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(dbDbUsers.getUsrId())});
    }

    // Deleting dbDbUsers
    public void removeUsers(dbUsers dbDbUsers) {

        database.delete(dbDatabaseHelper.TABLE_USERS, dbDatabaseHelper.KEY_ID + "=" + dbDbUsers.getUsrId(), null);
    }

    //---------------------LOAN APPLICATIONS ---------------------------------------------//
    // Add New Loan Application
    public dbApplications addApplication(dbApplications dbapplcns) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_AMOUNT, dbapplcns.getAppAmt());
        values.put(dbDatabaseHelper.KEY_DATE, dbapplcns.getAppDate());
        values.put(dbDatabaseHelper.COLUMN_SECTOR, dbapplcns.getAppSector());
        values.put(dbDatabaseHelper.COLUMN_TENURE, dbapplcns.getAppTenure());
        values.put(dbDatabaseHelper.COLUMN_STATUS, dbapplcns.getAppStatus());
        values.put(dbDatabaseHelper.COLUMN_OTHER_INFO, dbapplcns.getAppInfo());
        values.put(dbDatabaseHelper.COLUMN_APP_ID, dbapplcns.getApplcnId());
        values.put(dbDatabaseHelper.COLUMN_ADDRESS, dbapplcns.getApplcn_address());
        values.put(dbDatabaseHelper.COLUMN_ASSETS, dbapplcns.getApplcn_assets());
        values.put(dbDatabaseHelper.COLUMN_EXPENSE, dbapplcns.getApplcn_expense());
        values.put(dbDatabaseHelper.COLUMN_INCOME, dbapplcns.getApplcn_income());
        values.put(dbDatabaseHelper.COLUMN_TYPE, dbapplcns.getApplcnType());
        values.put(dbDatabaseHelper.COLUMN_OPS_LENGTH,dbapplcns.getOps_length());
        long insertid = database.insert(dbDatabaseHelper.TABLE_APPLICATIONS, null, values);
        dbapplcns.setAppId(insertid);
        return dbapplcns;

    }

    // Getting single Loan Application
    public dbApplications getApplication(long id) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_APPLICATIONS, allApplicationColumns, dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        dbApplications e = new dbApplications(Long.parseLong(cursor.getString(0)), Double.valueOf(cursor.getString(1)), cursor.getString(2), cursor.getString(5), cursor.getString(6), cursor.getString(3), cursor.getString(4),cursor.getString(7), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12),cursor.getString(13),cursor.getString(14));
        // return dbApplication
        return e;
    }

    public List<dbApplications> getAllApplications() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_APPLICATIONS, allApplicationColumns, null, null, null, null,dbDatabaseHelper.KEY_ID + " DESC");

        List<dbApplications> dbapps = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbApplications dbapps1 = new dbApplications();
                dbapps1.setAppId(cursor.getLong(cursor.getColumnIndex(dbDatabaseHelper.KEY_ID)));
                dbapps1.setAppAmt(Double.valueOf(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_AMOUNT))));
                dbapps1.setAppDate(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE)));
                dbapps1.setAppInfo(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_OTHER_INFO)));
                dbapps1.setAppTenure(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_TENURE)));
                dbapps1.setAppSector(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_SECTOR)));
                dbapps1.setAppStatus(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_STATUS)));
                dbapps1.setApplcnId(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_APP_ID)));
                dbapps.add(dbapps1);
            }
        }
        return dbapps;
    }

    public boolean applicationCount() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_APPLICATIONS, allApplicationColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            return false;
        }
        return true;
    }

    // Updating dbUsers
    public int updateApplication(dbApplications dbapplcns) {

        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_AMOUNT, dbapplcns.getAppAmt());
        values.put(dbDatabaseHelper.KEY_DATE, dbapplcns.getAppDate());
        values.put(dbDatabaseHelper.COLUMN_SECTOR, dbapplcns.getAppSector());
        values.put(dbDatabaseHelper.COLUMN_TENURE, dbapplcns.getAppTenure());
        values.put(dbDatabaseHelper.COLUMN_STATUS, dbapplcns.getAppStatus());
        values.put(dbDatabaseHelper.COLUMN_OTHER_INFO, dbapplcns.getAppInfo());
        values.put(dbDatabaseHelper.COLUMN_APP_ID, dbapplcns.getApplcnId());
        values.put(dbDatabaseHelper.COLUMN_ADDRESS, dbapplcns.getApplcn_address());
        values.put(dbDatabaseHelper.COLUMN_ASSETS, dbapplcns.getApplcn_assets());
        values.put(dbDatabaseHelper.COLUMN_EXPENSE, dbapplcns.getApplcn_expense());
        values.put(dbDatabaseHelper.COLUMN_INCOME, dbapplcns.getApplcn_income());
        values.put(dbDatabaseHelper.COLUMN_TYPE, dbapplcns.getApplcnType());
        // updating row
        return database.update(dbDatabaseHelper.TABLE_APPLICATIONS, values,
                dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(dbapplcns.getAppId())});
    }

    // Deleting dbUsers
    public void removeApplication(dbApplications dbapplications) {

        database.delete(dbDatabaseHelper.TABLE_APPLICATIONS, dbDatabaseHelper.KEY_ID + "=" + dbapplications.getAppId(), null);
    }


//------------------------MOBILE MONEY----------------------------------------------------//

    // Getting single Loan Application
    public dbMobileMoney getMobileMoney(long id) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_MOBILE_MONEY, allColumns, dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        dbMobileMoney e = new dbMobileMoney(Long.parseLong(cursor.getString(0)), Double.valueOf(cursor.getString(1)), cursor.getString(2), Date.valueOf(cursor.getString(3)), cursor.getString(4));
        // return dbApplication
        return e;
    }

    public List<dbMobileMoney> getAllMobileMoney() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_MOBILE_MONEY, allColumns, null, null, null, null, null);

        List<dbMobileMoney> dbmm = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbMobileMoney dbmm1 = new dbMobileMoney();
                dbmm1.setMobId(cursor.getLong(cursor.getColumnIndex(dbDatabaseHelper.KEY_ID)));
                dbmm1.setMobAmt(Double.valueOf(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_AMOUNT))));
                dbmm1.setAppDate(Date.valueOf(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE))));
                dbmm1.setMobName(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_NAME)));
                dbmm1.setMobType(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_TYPE)));
                dbmm.add(dbmm1);
            }
        }
        // return All dbUserss
        return dbmm;
    }

    public boolean mobileCount() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_MOBILE_MONEY, allColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            return false;
        }
        return true;
    }


//------------------------LOAN ACCOUNTS--------------------------------------------------//

    // Add New Loan Account
    public dbLoans addLoan(dbLoans dbloans) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_LOAN_BAL,dbloans.getLn_balance());
        values.put(dbDatabaseHelper.KEY_INT_RATE,dbloans.getLn_int_rate());
        values.put(dbDatabaseHelper.KEY_PR_PAID,dbloans.getLn_pr_paid());
        values.put(dbDatabaseHelper.COLUMN_TENURE,dbloans.getLn_tenure());
        values.put(dbDatabaseHelper.KEY_INT_PAID,dbloans.getLn_int_paid());
        values.put(dbDatabaseHelper.KEY_FEE_PAID,dbloans.getLn_fee_paid());
        values.put(dbDatabaseHelper.KEY_LOAN_AMNT,dbloans.getLn_amount());
        values.put(dbDatabaseHelper.KEY_ACC_NUM,dbloans.getLn_acc_num());
        values.put(dbDatabaseHelper.KEY_SET_ACC,dbloans.getLn_set_num());
        values.put(dbDatabaseHelper.KEY_ACC_STATE,dbloans.getLn_acc_sate());
        values.put(dbDatabaseHelper.KEY_AMT_DUE,dbloans.getLn_amt_due());
        long insertid = database.insert(dbDatabaseHelper.TABLE_LOANS, null, values);
        dbloans.setLn_id(insertid);
        return dbloans;
    }

    // Getting single Loan Account
    public dbLoans getLoan(String id) {
        Log.i("Loan ID",id);
        dbLoans e = null;
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_LOANS, allLoanColumns, dbDatabaseHelper.KEY_ACC_NUM + "=?", new String[]{id}, null, null, null, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                e = new dbLoans(Long.parseLong(cursor.getString(0)), (cursor.getString(1)), (cursor.getString(4)), (cursor.getString(2)), (cursor.getString(3)), (cursor.getString(7)), (cursor.getString(6)), (cursor.getString(5)), cursor.getString(8), cursor.getString(9), cursor.getString(10), cursor.getString(11));
            }
        }
        return e;
    }

    // Getting single Loan Account
    public dbLoans getActiveLoan() {
        String[] id = {"ACTIVE","ACTIVE_IN_ARREARS"};
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_LOANS, allLoanColumns, dbDatabaseHelper.KEY_ACC_STATE + "=? OR " + dbDatabaseHelper.KEY_ACC_STATE + "=?", id, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        dbLoans e = new dbLoans(Long.parseLong(cursor.getString(0)), (cursor.getString(1)),(cursor.getString(4)),(cursor.getString(2)), (cursor.getString(3)), (cursor.getString(7)), (cursor.getString(6)), (cursor.getString(5)),cursor.getString(8),cursor.getString(9),cursor.getString(10),cursor.getString(11));
        return e;
    }

    public List<dbLoans> getAllLoans() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_LOANS, allLoanColumns, null, null, null, null, null);

        List<dbLoans> loans = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbLoans loan =  new dbLoans();
                loan.setLn_id(cursor.getLong(cursor.getColumnIndex(dbDatabaseHelper.KEY_ID)));
                loan.setLn_acc_num(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_ACC_NUM)));
                loan.setLn_acc_sate(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_ACC_STATE)));
                loan.setLn_amount(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_LOAN_AMNT)));
                loan.setLn_balance(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_LOAN_BAL)));
                loan.setLn_fee_paid(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_FEE_PAID)));
                loan.setLn_int_paid(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_INT_PAID)));
                loan.setLn_int_rate(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_INT_RATE)));
                loan.setLn_pr_paid(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_PR_PAID)));
                loan.setLn_tenure(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_TENURE)));
                loan.setLn_amt_due(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_AMT_DUE)));
                loans.add(loan);

            }
        }
        return loans;
    }

    public boolean loanCount() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_LOANS, allLoanColumns, null, null, null, null, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }
    // Updating Loans
    public int updateLoans(dbLoans dbloans) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_LOAN_BAL,dbloans.getLn_balance());
        values.put(dbDatabaseHelper.KEY_INT_RATE,dbloans.getLn_int_rate());
        values.put(dbDatabaseHelper.KEY_PR_PAID,dbloans.getLn_pr_paid());
        values.put(dbDatabaseHelper.COLUMN_TENURE,dbloans.getLn_tenure());
        values.put(dbDatabaseHelper.KEY_INT_PAID,dbloans.getLn_int_paid());
        values.put(dbDatabaseHelper.KEY_FEE_PAID,dbloans.getLn_fee_paid());
        values.put(dbDatabaseHelper.KEY_LOAN_AMNT,dbloans.getLn_amount());
        values.put(dbDatabaseHelper.KEY_ACC_NUM,dbloans.getLn_acc_num());
        values.put(dbDatabaseHelper.KEY_SET_ACC,dbloans.getLn_set_num());
        values.put(dbDatabaseHelper.KEY_ACC_STATE,dbloans.getLn_acc_sate());
        values.put(dbDatabaseHelper.KEY_AMT_DUE,dbloans.getLn_amt_due());
        // updating row
        return database.update(dbDatabaseHelper.TABLE_LOANS, values,
                dbDatabaseHelper.KEY_ACC_NUM + "=?", new String[]{dbloans.getLn_acc_num()});
    }

    public void removeLoans(dbLoans loans) {

        database.delete(dbDatabaseHelper.TABLE_LOANS, dbDatabaseHelper.KEY_ID + "=" + loans.getLn_id(), null);
    }


//---------------------SECURITIES --------------------------------------------------------------//

    // Add Security Request
    public dbSecurities addSecurity(dbSecurities security) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_SEC_DATE,security.getSec_date());
        values.put(dbDatabaseHelper.KEY_SEC_DETAILS,security.getSec_details());
        values.put(dbDatabaseHelper.COLUMN_STATUS,security.getSec_state());
        values.put(dbDatabaseHelper.KEY_DATE,security.getSec_create_date());
        values.put(dbDatabaseHelper.KEY_SEC_TYPE,security.getSec_type());
        long insertid = database.insert(dbDatabaseHelper.TABLE_SECURITY, null, values);
        security.setSec_id(insertid);
        return security;
    }

    // Getting single Security Request
    public dbSecurities getSecurity(long id) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SECURITY, allSecurityColumns, dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        dbSecurities e = new dbSecurities(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5));

        return e;
    }

    public List<dbSecurities> getAllSecurity() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SECURITY, allSecurityColumns, null, null, null, null, dbDatabaseHelper.KEY_ID + " DESC");

        List<dbSecurities> securities = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbSecurities security =  new dbSecurities();
                security.setSec_create_date(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE)));
                security.setSec_date(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_SEC_DATE)));
                security.setSec_details(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_SEC_DETAILS)));
                security.setSec_state(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_STATUS)));
                security.setSec_type(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_SEC_TYPE)));
                securities.add(security);
            }
        }
        return securities;
    }

    public void removeSecurity(dbSecurities security) {

        database.delete(dbDatabaseHelper.TABLE_SECURITY, dbDatabaseHelper.KEY_ID + "=" + security.getSec_id(), null);
    }
    

// ------------------------FEEDBACK---------------------------------------------------------------//
    // Add Security Request
    public dbFeedback addFeedback(dbFeedback feedback) {
    ContentValues values = new ContentValues();
    values.put(dbDatabaseHelper.KEY_FD_DETAILS,feedback.getFd_details());
    values.put(dbDatabaseHelper.COLUMN_STATUS,feedback.getFd_state());
    values.put(dbDatabaseHelper.KEY_DATE,feedback.getFd_date());
        values.put(dbDatabaseHelper.KEY_FD_TITLE,feedback.getFd_title());
    long insertid = database.insert(dbDatabaseHelper.TABLE_FEEDBACK, null, values);
    feedback.setFd_id(insertid);
    return feedback;
}

    // Getting single Security Request
    public dbFeedback getFeedback(long id) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_FEEDBACK, allFeedbackColumns, dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        dbFeedback e = new dbFeedback(Long.parseLong(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4));

        return e;
    }

    public List<dbFeedback> getAllFeedback() {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_FEEDBACK, allFeedbackColumns, null, null, null, null, dbDatabaseHelper.KEY_ID + " DESC");

        List<dbFeedback> feedbacks = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbFeedback feedback =  new dbFeedback();
                feedback.setFd_date(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE)));
                feedback.setFd_details(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_FD_DETAILS)));
                feedback.setFd_state(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.COLUMN_STATUS)));
                feedback.setFd_title(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_FD_TITLE)));
                feedbacks.add(feedback);
            }
        }
        return feedbacks;
    }

    public void removeFeedback(dbFeedback feedback) {

        database.delete(dbDatabaseHelper.TABLE_FEEDBACK, dbDatabaseHelper.KEY_ID + "=" + feedback.getFd_id(), null);
    }

    //------------------------LOAN Schedules--------------------------------------------------//
    // Add New Loan Schedule
    public dbSchedules addSchedule(dbSchedules schedules) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_AMT_DUE,schedules.getAmount_due());
        values.put(dbDatabaseHelper.KEY_ACC_NUM,schedules.getLn_account());
        values.put(dbDatabaseHelper.KEY_AMT_PAID,schedules.getAmount_paid());
        values.put(dbDatabaseHelper.KEY_ACC_STATE,schedules.getState());
        values.put(dbDatabaseHelper.KEY_DATE,schedules.getDue_date());
        long insertid = database.insert(dbDatabaseHelper.TABLE_SCHEDULE, null, values);
        schedules.setId(insertid);
        return schedules;
    }

    // Getting single Loan Account
    public dbSchedules getSchedule(String account,String date) {
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SCHEDULE, allScheduleColumns, dbDatabaseHelper.KEY_ACC_NUM + " = ? AND " + dbDatabaseHelper.KEY_DATE  + " = ?", new String[]{account,date}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        dbSchedules e = new dbSchedules(Long.parseLong(cursor.getString(0)), (cursor.getString(1)),(cursor.getString(2)),(cursor.getString(3)), (cursor.getString(4)), (cursor.getString(5)));
        // return dbApplication
        return e;
    }

    public boolean ScheduleCount(String account,String date) {
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SCHEDULE, allScheduleColumns,dbDatabaseHelper.KEY_ACC_NUM + " = ? AND " + dbDatabaseHelper.KEY_DATE  + " = ?", new String[]{account,date}, null, null, null);
        if (cursor.getCount() > 0) {
            return false;
        }
        return true;
    }

    public boolean ScheduleCountAll(String account) {
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SCHEDULE, allScheduleColumns,dbDatabaseHelper.KEY_ACC_NUM + " = ?" , new String[]{account}, null, null, null);
        if (cursor.getCount() > 0) {
            return true;
        }
        return false;
    }

    public dbSchedules getNextSchedule(String account)
    {
        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SCHEDULE, allScheduleColumns,  dbDatabaseHelper.KEY_ACC_NUM  + " = ? AND " + dbDatabaseHelper.KEY_ACC_STATE  + " != ?", new String[]{account,"PAID"}, null, null, dbDatabaseHelper.KEY_ID + " ASC","1");
        if (cursor != null)
            cursor.moveToFirst();

        dbSchedules e = new dbSchedules(Long.parseLong(cursor.getString(0)), (cursor.getString(1)),(cursor.getString(2)),(cursor.getString(3)), (cursor.getString(4)), (cursor.getString(5)));
        // return dbApplication
        return e;
    }

    public List<dbSchedules> getAllSchedule(String account) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_SCHEDULE, allScheduleColumns, dbDatabaseHelper.KEY_ACC_NUM + "=?", new String[]{account}, null, null, null, null);

        List<dbSchedules> schedules = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbSchedules schedule =  new dbSchedules();
                schedule.setId(cursor.getLong(cursor.getColumnIndex(dbDatabaseHelper.KEY_ID)));
                schedule.setLn_account(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_ACC_NUM)));
                schedule.setState(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_ACC_STATE)));
                schedule.setDue_date(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE)));
                schedule.setAmount_paid(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_AMT_PAID)));;
                schedule.setAmount_due(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_AMT_DUE)));
                schedules.add(schedule);
            }
        }
        return schedules;
    }

    // Updating Loans
    public int updateSchedule(dbSchedules schedules) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_DATE,schedules.getDue_date());
        values.put(dbDatabaseHelper.KEY_AMT_PAID,schedules.getAmount_paid());
        values.put(dbDatabaseHelper.KEY_ACC_NUM,schedules.getLn_account());
        values.put(dbDatabaseHelper.KEY_ACC_STATE,schedules.getState());
        values.put(dbDatabaseHelper.KEY_AMT_DUE,schedules.getAmount_due());
        // updating row
        return database.update(dbDatabaseHelper.TABLE_SCHEDULE, values,
                dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(schedules.getId())});
    }

    public void removeSchedule(dbSchedules schedules) {

        database.delete(dbDatabaseHelper.TABLE_SCHEDULE, dbDatabaseHelper.KEY_ID + "=" + schedules.getId(), null);
    }

    //------------------------LOAN Transactions--------------------------------------------------//
    // Add Transactions
    public dbTransactions addTransaction(dbTransactions transactions) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_AMT,transactions.getTran_amount());
        values.put(dbDatabaseHelper.KEY_BAL,transactions.getTran_balance());
        values.put(dbDatabaseHelper.KEY_TYPE,transactions.getTran_type());
        values.put(dbDatabaseHelper.KEY_ACC_NUM,transactions.getTran_account());
        values.put(dbDatabaseHelper.KEY_DATE,transactions.getTran_date());
        values.put(dbDatabaseHelper.TRANS_ID,transactions.getTrans_id());
        long insertid = database.insert(dbDatabaseHelper.TABLE_TRANSACTION, null, values);
        transactions.setId(insertid);
        return transactions;
    }
    // Get Transactions
    public List<dbTransactions> getAllTransactions(String account) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_TRANSACTION, allTransactionColumns, dbDatabaseHelper.KEY_ACC_NUM + "=?" , new String[]{account}, null, null,dbDatabaseHelper.KEY_ID + " ASC",null);

        List<dbTransactions> transactions = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                dbTransactions transaction =  new dbTransactions();
                transaction.setId(cursor.getLong(cursor.getColumnIndex(dbDatabaseHelper.KEY_ID)));
                transaction.setTran_account(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_ACC_NUM)));
                transaction.setTran_type(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_TYPE)));
                transaction.setTran_date(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_DATE)));
                transaction.setTran_amount(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_AMT)));;
                transaction.setTran_balance(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.KEY_BAL)));
                transaction.setTrans_id(cursor.getString(cursor.getColumnIndex(dbDatabaseHelper.TRANS_ID)));
                transactions.add(transaction);
            }
        }

        return transactions;
    }
    // Updating Loans
    public int updateTransaction(dbTransactions transactions) {
        ContentValues values = new ContentValues();
        values.put(dbDatabaseHelper.KEY_AMT,transactions.getTran_amount());
        values.put(dbDatabaseHelper.KEY_BAL,transactions.getTran_balance());
        values.put(dbDatabaseHelper.KEY_TYPE,transactions.getTran_type());
        values.put(dbDatabaseHelper.KEY_ACC_NUM,transactions.getTran_account());
        values.put(dbDatabaseHelper.KEY_DATE,transactions.getTran_date());
        // updating row
        return database.update(dbDatabaseHelper.TABLE_TRANSACTION, values,
                dbDatabaseHelper.KEY_ID + "=?", new String[]{String.valueOf(transactions.getId())});
    }

    public void removeTransaction(dbTransactions transactions) {

        database.delete(dbDatabaseHelper.TABLE_TRANSACTION, dbDatabaseHelper.KEY_ID + "=" + transactions.getId(), null);
    }

    public boolean TransactionsCount(String id) {

        Cursor cursor = database.query(dbDatabaseHelper.TABLE_TRANSACTION, allTransactionColumns, dbDatabaseHelper.TRANS_ID+ "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor.getCount() > 0) {
            return false;
        }
        return true;
    }

}
