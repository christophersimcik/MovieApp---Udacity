package com.example.csimcik.movieapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by csimcik on 5/18/2017.
 */
public class FavoriteDataB extends SQLiteOpenHelper {
    SQLiteDatabase database;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "favorites";
    private static final String COLUMN_ID = "ID";
    private static final String KEY_ID = "favoritemovie";
    private static final Boolean CAN_ADD = true;

    public FavoriteDataB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DATABASE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ID + " VARCHAR, " + CAN_ADD + " BOOLEAN);");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("create table " + DATABASE_NAME + " ( " + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ID + " VARCHAR, " + CAN_ADD + " BOOLEAN);");
        onCreate(db);
    }

    public void insertRecord(FavorItem favor) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, favor.getMovieId());
        database.insert(DATABASE_NAME, null, contentValues);
        database.close();
    }

    public void updateRecord(FavorItem favor) {
        database = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_ID, favor.getMovieId());
        database.update(DATABASE_NAME, contentValues, COLUMN_ID + " = ?", new String[]{});
        database.close();
    }
    public void deleteRecord(FavorItem favor) {
        database = this.getReadableDatabase();
        database.delete(DATABASE_NAME, KEY_ID + "=" + DetailSheetFragment.movieIdString, null);
        database.close();
    }
    public ArrayList<FavorItem> getAllRecords() {
        database = this.getReadableDatabase();
        Cursor cursor = database.query(DATABASE_NAME, null, null, null, null, null, null);
        ArrayList<FavorItem> favoriteListB = new ArrayList<FavorItem>();
        FavorItem favitem;
        if (cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                cursor.moveToNext();
                favitem = new FavorItem(cursor.getString(1),false);
                favoriteListB.add(favitem);
            }
        }
        cursor.close();
        database.close();
        return favoriteListB;
    }



}