package com.example.eric.friends;

/**
 * Created by Eric on 3/24/2015.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


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
                + " TEXT," + COLUMN_EMAIL + " TEXT," + COLUMN_PHONENO + "TEXT" + ")";
        db.execSQL(CREATE_FRIENDS_TABLE);
        db.execSQL(ctx.getString(R.string.insert));
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

        if (cursor.moveToFirst() == false)
            Toast.makeText(ctx, "Empty", Toast.LENGTH_SHORT).show();

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Friends friends = new Friends();
                friends.setID(Integer.parseInt(cursor.getString(0)));
                friends.setName(cursor.getString(1));
                friends.setEmail(cursor.getString(2));
                friends.setPhoneNo(cursor.getString(3));

                String name = cursor.getString(1);
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



