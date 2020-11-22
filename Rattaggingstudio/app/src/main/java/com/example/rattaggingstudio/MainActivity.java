package com.example.rattaggingstudio;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import androidx.annotation.NonNull;
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
//        Button addTag = (Button) view.findViewById(R.id.tag);
//        save.setOnClickListener(new View.OnClickListener() {
//            @Override
//             public void onClick(View view) {
        Button fab = findViewById(R.id.save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = sqlDataHelper.getData(FeedTag.FeedEntryTag.TABLE_NAME);
                System.out.println("Curor returnd");
                int index = cursor.getColumnIndex(FeedTag.FeedEntryTag.COLUMN_NAME_DESCRIPTION);
                System.out.println(index);
                cursor.moveToFirst();
                String test = cursor.getString(index); // TO NIE DZIALA
                System.out.println(test);
                cursor.moveToNext();
                String test1 = cursor.getString(index); // TO NIE DZIALA
                System.out.println(test1);

            }
        });
//
    }





}