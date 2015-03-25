package com.example.eric.friends;

/**
 * Created by Eric on 3/24/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONENO = "phoneno";
    private static final String DATABASE_NAME = "friendDB.db";
    private static final String TABLE_FRIENDS = "tables";
    public static Context ctx;


    public DBHelper(Context context, String name,
                    SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FRIENDS_TABLE = "CREATE TABLE " +
                TABLE_FRIENDS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_NAME
                + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONENO + "TEXT" + ")"
                + ctx.getString(R.string.insert);
        db.execSQL(CREATE_FRIENDS_TABLE);
        //query();

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        onCreate(db);
    }


    /*
    public void query(){
        String query = "Select name FROM " + TABLE_FRIENDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =  db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                cursor.moveToFirst();
                String Name = cursor.getString(cursor.getColumnIndex("Name"));
                email[count] = cursor.getString(cursor.getColumnIndex("Email"));
                phoneno[count] = cursor.getString(cursor.getColumnIndex("PhoneNumber"));
                results.add(Name);
                count++;
            } while (cursor.moveToNext());
            db.close();
            Toast.makeText(context.getApplicationContext(), "Done",
                    Toast.LENGTH_SHORT).show();
        }
    }
    */ //Note: don't think this will work here
}



