package com.example.eric.friends;

/**
 * Created by Eric on 3/24/2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PHONENO = "phonenumber";
    private static final String DATABASE_NAME = "friendDB.db";
    private static final String TABLE_FRIENDS = "friends";
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
                + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONENO + " TEXT" + ")";
        db.execSQL(CREATE_FRIENDS_TABLE);
        db.execSQL(ctx.getString(R.string.insert0));
        db.execSQL(ctx.getString(R.string.insert1));
        db.execSQL(ctx.getString(R.string.insert2));
        db.execSQL(ctx.getString(R.string.insert3));
        db.execSQL(ctx.getString(R.string.insert4));
        db.execSQL(ctx.getString(R.string.insert5));
        db.execSQL(ctx.getString(R.string.insert6));
        db.execSQL(ctx.getString(R.string.insert7));
        db.execSQL(ctx.getString(R.string.insert8));
        db.execSQL(ctx.getString(R.string.insert9));

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        onCreate(db);
    }

    public List<Friends> getAllContacts() {
        List<Friends> friendList = new ArrayList<Friends>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_FRIENDS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        int id = cursor.getColumnIndex(COLUMN_ID);
        int nm = cursor.getColumnIndex(COLUMN_NAME);
        int em = cursor.getColumnIndex(COLUMN_EMAIL);
        int num = cursor.getColumnIndex(COLUMN_PHONENO);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Friends friends = new Friends();
                friends.setID(Integer.parseInt(cursor.getString(id)));
                friends.setName(cursor.getString(nm));
                friends.setEmail(cursor.getString(em));
                friends.setPhoneNo(cursor.getString(num));

                String name = cursor.getString(nm);
                MainActivity.ArrayofName.add(name);
                // Adding contact to list
                friendList.add(friends);
            } while (cursor.moveToNext());
        }

        // return contact list
        db.close();
        return friendList;
    }
}



