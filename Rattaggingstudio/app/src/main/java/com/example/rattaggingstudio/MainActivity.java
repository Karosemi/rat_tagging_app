package com.example.rattaggingstudio;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    public static SqlDataHelper sqlDataHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlDataHelper = new SqlDataHelper(this);
        int oldVersion = 0;
        int newVersion = 1;
        sqlDataHelper.onUpgrade(sqlDataHelper.getWritableDatabase(), oldVersion, newVersion);

    }




}