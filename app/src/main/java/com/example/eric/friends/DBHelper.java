package com.example.eric.friends;

/**
 * Created by Eric on 3/24/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
    public static final int version = '1';
    public static String DBName = "Friends";
    public static Context currentContext;
    public static String tableName = "Friends";
    public SQLiteDatabase DB;
    public String DBPath;


    public DBHelper(Context context) {
        super(context, DBName, null, version);
        currentContext = context;
        DBPath = "/data/data/" + context.getPackageName() + "/databases";
        createDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
/*
    private boolean checkDbExists() {
        SQLiteDatabase checkDB = null;

        try {
            String myPath = DBPath + DBName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null,
                    SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            // database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    } */

    private void createDatabase() {
        DB = currentContext.openOrCreateDatabase(DBName, 0, null);
        DB.execSQL("CREATE TABLE IF NOT EXISTS " +
                tableName +
                " (Name VARCHAR, Email VARCHAR," +
                " PhoneNumber VARCHAR);");
        DB.execSQL(currentContext.getString(R.string.insert));
    }
}



