package com.example.eric.friends;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    private ArrayList<String> results = new ArrayList<String>();
    private String[] email;
    private String[] phoneno;
    private int count = 0;

    private String tableName = DBHelper.tableName;
    private SQLiteDatabase newDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openAndQueryDatabase();

        displayResultList();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayResultList() {
        ListView lv = (ListView) findViewById(R.id.list);
        ArrayAdapter aa = new ArrayAdapter(this, R.layout.activity_main, results);
        lv.setAdapter(aa);
    }

    private void openAndQueryDatabase() {
        try {
            DBHelper dbHelper = new DBHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();
            Cursor c = newDB.rawQuery("SELECT Name FROM " +
                    tableName, null);

            if (c != null) {
                if (c.moveToFirst()) {
                    do {
                        String Name = c.getString(c.getColumnIndex("Name"));
                        email[count] = c.getString(c.getColumnIndex("Email"));
                        phoneno[count] = c.getString(c.getColumnIndex("PhoneNumber"));
                        results.add(Name);
                        count++;
                    } while (c.moveToNext());
                }
            }
        } catch (SQLiteException se) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        } finally {
            if (newDB != null)
                newDB.execSQL("DELETE FROM " + tableName);
        }
        newDB.close();

    }
}
