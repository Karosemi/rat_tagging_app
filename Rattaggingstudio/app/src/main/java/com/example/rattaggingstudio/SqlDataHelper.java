package com.example.rattaggingstudio;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class SqlDataHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "tagging";
    private static final String SQL_CREATE_INFO_ENTRIES =
            "CREATE TABLE " + FeedInfo.FeedEntryInfo.TABLE_NAME + " (" +
                    FeedInfo.FeedEntryInfo._ID + " INTEGER PRIMARY KEY," +
                    FeedInfo.FeedEntryInfo.COLUMN_NAME_TITLE + " TEXT," +
                    FeedInfo.FeedEntryInfo.COLUMN_NAME_DATE + " TEXT)";

    private static final String SQL_DELETE_INFO_ENTRIES =
             "DROP TABLE IF EXISTS " + FeedInfo.FeedEntryInfo.TABLE_NAME;

    private static final String SQL_CREATE_TAG_ENTRIES =
            "CREATE TABLE " + FeedTag.FeedEntryTag.TABLE_NAME + "(" +
                    FeedTag.FeedEntryTag._ID + " INTEGER PRIMARY KEY," +
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
        ContentValues values = new ContentValues();
        values.put(FeedInfo.FeedEntryInfo.COLUMN_NAME_TITLE, title);
        values.put(FeedInfo.FeedEntryInfo.COLUMN_NAME_DATE, date);
        Log.d(TAG, "addDataToInfoTable: Adding data to table" + FeedInfo.FeedEntryInfo.TABLE_NAME);
        long result = dbInfo.insert(FeedInfo.FeedEntryInfo.TABLE_NAME, null, values);
        return result != -1;

    }
    public boolean addDataToTagTable(String fileNumber, String description){
        SQLiteDatabase dbInfo = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(FeedTag.FeedEntryTag.COLUMN_NAME_NUMBER, fileNumber);
        values.put(FeedTag.FeedEntryTag.COLUMN_NAME_DESCRIPTION, description);
        Log.d(TAG, "addDataToInfoTable: Adding data to table" + FeedTag.FeedEntryTag.TABLE_NAME);
        long result = dbInfo.insert(FeedTag.FeedEntryTag.TABLE_NAME, null, values);
        return result != -1;



    }
    public Cursor getData(String tableName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + tableName +";";
        return db.rawQuery(query, null);
    }

}
