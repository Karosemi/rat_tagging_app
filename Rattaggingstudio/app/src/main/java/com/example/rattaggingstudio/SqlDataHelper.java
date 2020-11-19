package com.example.rattaggingstudio;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.nio.file.FileAlreadyExistsException;

public class SqlDataHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tagging";
    private static final String SQL_CREATE_INFO_ENTRIES =
            "CREATE TABLE " + FeedInfo.FeedEntryInfo.TABLE_NAME + " (" +
                    FeedInfo.FeedEntryInfo._ID + " INTEGER PRIMARY KEY," +
                    FeedInfo.FeedEntryInfo.COLUMN_NAME_ID + " TEXT," +
                    FeedInfo.FeedEntryInfo.COLUMN_NAME_TITLE + " TEXT," +
                    FeedInfo.FeedEntryInfo.COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_INFO_ENTRIES =
             "DROP TABLE IF EXISTS " + FeedInfo.FeedEntryInfo.TABLE_NAME;

    private static final String SQL_CREATE_TAG_ENTRIES =
            "CREATE TABLE " + FeedTag.FeedEntryTag.TABLE_NAME + "(" +
                    FeedTag.FeedEntryTag._ID + " INTEGER PRIMARY KEY," +
                    FeedTag.FeedEntryTag.COLUMN_NAME_INFO_ID + " TEXT," +
                    FeedTag.FeedEntryTag.COLUMN_NAME_ID + " TEXT," +
                    FeedTag.FeedEntryTag.COLUMN_NAME_NUMBER  + " TEXT," +
                    FeedTag.FeedEntryTag.COLUMN_NAME_DESCRIPTION + " TEXT)";

    private static final String SQL_DELETE_TAG_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedTag.FeedEntryTag.TABLE_NAME;
    public SqlDataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_INFO_ENTRIES);
        db.execSQL(SQL_CREATE_TAG_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_INFO_ENTRIES);
        db.execSQL(SQL_DELETE_TAG_ENTRIES);
        onCreate(db);
    }


    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    public boolean addDataToInfoTable(String title, String date) {
        SQLiteDatabase dbInfo = this.getWritableDatabase();
        ContentValues titleContentValues = new ContentValues();
        ContentValues dateContentValues = new ContentValues();
        titleContentValues.put(FeedInfo.FeedEntryInfo.COLUMN_NAME_TITLE, title);
        dateContentValues.put(FeedInfo.FeedEntryInfo.COLUMN_NAME_DATE, date);
        long titleResult = dbInfo.insert(FeedInfo.FeedEntryInfo.TABLE_NAME, null, titleContentValues);
        long dateResult = dbInfo.insert(FeedInfo.FeedEntryInfo.TABLE_NAME, null, dateContentValues);
        Log.d(TAG, "addDataToInfoTable: Adding data to table" + FeedInfo.FeedEntryInfo.TABLE_NAME);
        return !(titleResult == -1 | dateResult == -1);

    }
//    public boolean

}
