package com.example.basicapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BasicAppDbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "BasicApp.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + BasicAppDBContract.LoginEntry.TABLE_NAME + " (" +
                    BasicAppDBContract.LoginEntry._ID + " INTEGER PRIMARY KEY," +
                    BasicAppDBContract.LoginEntry.COLUMN_NAME_LOGIN + " TEXT," +
                    BasicAppDBContract.LoginEntry.COLUMN_NAME_PASSWORD + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + BasicAppDBContract.LoginEntry.TABLE_NAME;


    public BasicAppDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
