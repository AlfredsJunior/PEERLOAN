package com.alfredtechsystems.myapplication2.dataclasses;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ashbel on 3/8/2017.
 */

public class dbDatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 125;

    // Database Name
    private static final String DATABASE_NAME = "untuMobile";

    // Table Names
    public static final String TABLE_USERS = "users";
    public static final String TABLE_APPLICATIONS = "applications";
    public static final String TABLE_MOBILE_MONEY = "mobile_money";
    public static final String TABLE_LOANS = "loans";
    public static final String TABLE_SECURITY = "securities";
    public static final String TABLE_FEEDBACK = "feedback";
    public static final String TABLE_SCHEDULE = "schedules";
    public static final String TABLE_TRANSACTION = "tran_table";

    //Common column names
    public static final String KEY_ID = "id";
    public static final String KEY_DATE = "create_date";
    public static final String KEY_AMOUNT = "amount";
    public static final String COLUMN_TENURE = "tenure";
    public static final String COLUMN_STATUS= "status";

    // USERS Table - column names
    public static final String COLUMN_FIRST_NAME = "firstname";
    public static final String COLUMN_LAST_NAME = "lastname";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ID_NUM= "idNum";
    public static final String COLUMN_PH_NUM= "phNum";
    public static final String COLUMN_PSWD= "Password";
    public static final String COLUMN_APP_KEY = "appKey";
    public static final String COLUMN_MAMBU_ID ="mambu_id";

    // APPLICATIONS Table - column names
    public static final String COLUMN_SECTOR = "sector";
    public static final String COLUMN_OTHER_INFO = "other_info";
    public static final String COLUMN_APP_ID = "appId";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_INCOME = "income";
    public static final String COLUMN_EXPENSE = "expense";
    public static final String COLUMN_ASSETS = "assets";
    public static final String COLUMN_COLLATERAL = "collateral";
    public static final String COLUMN_TYPE= "client_type";
    public static final String COLUMN_OPS_LENGTH= "ops_length";

    // MOBILE_MONEY Table - column names
    public static final String KEY_NAME = "tran_name";
    public static final String KEY_TYPE = "tran_type";

    // LOAN_MONEY Table - column names
    public static final String KEY_LOAN_BAL = "loan_bal";
    public static final String KEY_INT_RATE = "int_rate";
    public static final String KEY_PR_PAID = "prin_paid";
    public static final String KEY_INT_PAID = "int_paid";
    public static final String KEY_FEE_PAID = "fee_paid";
    public static final String KEY_LOAN_AMNT = "loan_amnt";
    public static final String KEY_ACC_NUM = "acc_num";
    public static final String KEY_SET_ACC = "set_acc";
    public static final String KEY_ACC_STATE = "acc_state";
    public static final String KEY_AMT_DUE = "amt_due";

    // SECURITIES Table - column names
    public static final String KEY_SEC_DETAILS = "sec_details";
    public static final String KEY_SEC_DATE = "sec_date";
    public static final String KEY_SEC_TYPE = "sec_type";

    // FEEDBACK Table - column names
    public static final String KEY_FD_DETAILS = "fd_details";
    public static final String KEY_FD_TITLE = "fd_title";

    // SCHEDULE Table - column names
    public static final String KEY_AMT_PAID = "amt_paid";

    // TRANSACTION Table - column names
    public static final String KEY_AMT = "amount";
    public static final String KEY_BAL = "balance";
    public static final String TRANS_ID ="trans_id";


    // Table Create Statements

    // dbUsers Create Statement
    private static final String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + " (" +
            KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_FIRST_NAME + " TEXT, " + COLUMN_LAST_NAME + " TEXT, " +
            COLUMN_GENDER + " TEXT, " + COLUMN_ID_NUM + " TEXT, " +
            COLUMN_PH_NUM + " TEXT, " +  COLUMN_PSWD + " TEXT, " +
            COLUMN_APP_KEY + " TEXT, " + COLUMN_STATUS + " TEXT, " +  COLUMN_MAMBU_ID + " TEXT, " +KEY_DATE + " TEXT " + ")";

    // dbApplications table create statement
    private static final String CREATE_TABLE_APPLICATIONS = "CREATE TABLE "
            + TABLE_APPLICATIONS + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_AMOUNT
            + " INTEGER," + COLUMN_TENURE + " INTEGER," + KEY_DATE + " TEXT, " + COLUMN_STATUS
            + " INTEGER," + COLUMN_ADDRESS + " TEXT," + COLUMN_ASSETS + " TEXT, " + COLUMN_COLLATERAL
            + " TEXT," + COLUMN_TYPE + " TEXT," + COLUMN_INCOME + " TEXT, " + COLUMN_EXPENSE
            + " TEXT, " + COLUMN_SECTOR + " TEXT," + COLUMN_OTHER_INFO + " TEXT, " + COLUMN_APP_ID + " TEXT, "
            + COLUMN_OPS_LENGTH + " TEXT " + ")";

    // Mobile Money table create statement
    private static final String CREATE_TABLE_MOBILE = "CREATE TABLE " + TABLE_MOBILE_MONEY
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AMOUNT + " INTEGER,"
            + KEY_NAME + " TEXT " + KEY_TYPE + " TEXT " + KEY_DATE + " DATETIME " + ")";

    // dbLoans table create statement
    private static final String CREATE_TABLE_LOANS = "CREATE TABLE "
            + TABLE_LOANS + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_LOAN_BAL + " INTEGER," + KEY_INT_RATE + " INTEGER,"
            + KEY_PR_PAID + " INTEGER," + COLUMN_TENURE + " INTEGER,"
            + KEY_INT_PAID + " INTEGER," + KEY_FEE_PAID + " INTEGER,"
            + KEY_LOAN_AMNT + " INTEGER," + KEY_ACC_NUM + " TEXT,"
            + KEY_SET_ACC + " TEXT," + KEY_ACC_STATE + " TEXT,"
            + KEY_AMT_DUE + " TEXT," + KEY_DATE + " TEXT " + ")";

    // Securities table create statement
    private static final String CREATE_TABLE_SECURITY = "CREATE TABLE " + TABLE_SECURITY
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_SEC_DETAILS + " TEXT,"
            + KEY_SEC_DATE + " TEXT, " + COLUMN_STATUS + " TEXT, " + KEY_DATE + " TEXT," +
            KEY_SEC_TYPE + " TEXT  " + ")";

    // Feedback table create statement
    private static final String CREATE_TABLE_FEEDBACK = "CREATE TABLE " + TABLE_FEEDBACK
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FD_DETAILS + " TEXT,"
            +  COLUMN_STATUS + " TEXT, " + KEY_DATE + " TEXT, "
            + KEY_FD_TITLE + " TEXT " +")";


    private static final String CREATE_TABLE_SCHEDULE = "CREATE TABLE "
            + TABLE_SCHEDULE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_AMT_PAID + " TEXT," + KEY_ACC_STATE + " TEXT,"+ KEY_ACC_NUM + " TEXT,"
            + KEY_AMT_DUE + " TEXT," + KEY_DATE + " TEXT " + ")";

    private static final String CREATE_TABLE_TRANSACTION = "CREATE TABLE "
            + TABLE_TRANSACTION + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_AMT + " TEXT," + KEY_BAL + " TEXT,"+ KEY_ACC_NUM + " TEXT,"
            + KEY_TYPE + " TEXT," + KEY_DATE + " TEXT ," + TRANS_ID + " TEXT " + ")";

    public dbDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USERS);
        db.execSQL(CREATE_TABLE_APPLICATIONS);
        db.execSQL(CREATE_TABLE_MOBILE);
        db.execSQL(CREATE_TABLE_LOANS);
        db.execSQL(CREATE_TABLE_FEEDBACK);
        db.execSQL(CREATE_TABLE_SECURITY);
        db.execSQL(CREATE_TABLE_SCHEDULE);
        db.execSQL(CREATE_TABLE_TRANSACTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_APPLICATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOBILE_MONEY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOANS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FEEDBACK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECURITY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SCHEDULE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        // create new tables
        onCreate(db);
    }
}
