package com.example.rattaggingstudio;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.service.quicksettings.Tile;
import android.view.View;
import android.widget.Button;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

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
                Cursor tagCursor = sqlDataHelper.getData(FeedTag.FeedEntryTag.TABLE_NAME);
                Cursor infoCursor = sqlDataHelper.getData(FeedInfo.FeedEntryInfo.TABLE_NAME);
                int indexTitle = infoCursor.getColumnIndex(FeedInfo.FeedEntryInfo.COLUMN_NAME_TITLE);
                int indexDate = infoCursor.getColumnIndex(FeedInfo.FeedEntryInfo.COLUMN_NAME_DATE);
                int indexDescription = tagCursor.getColumnIndex(FeedTag.FeedEntryTag.COLUMN_NAME_DESCRIPTION);
                int indexFileNumber = tagCursor.getColumnIndex(FeedTag.FeedEntryTag.COLUMN_NAME_NUMBER);
                infoCursor.moveToFirst();
                StringBuilder tag = new StringBuilder();
                String title = infoCursor.getString(indexTitle);
                String date = infoCursor.getString(indexDate);
                tag.append("Title,Date\n" + title + "," + date);
                tag.append("\nFileNumber,Description");
                tagCursor.moveToFirst();
                int count = tagCursor.getCount();
                System.out.println(count);
                if (count == 1) {
                    String description = tagCursor.getString(indexDescription);
                    String fileNumber = tagCursor.getString(indexFileNumber);
                    tag.append("\n" + fileNumber + "," + description);
                }
                else if (count > 1){
                for (int i = 0; i < count; i += 1) {
                    String description = tagCursor.getString(indexDescription);
                    String fileNumber = tagCursor.getString(indexFileNumber);
                    tag.append("\n" + fileNumber + "," + description);
                    tagCursor.moveToNext();
                }}
                System.out.println(tag.toString());
                try {
                    FileOutputStream out = openFileOutput("data.csv", Context.MODE_PRIVATE);
                    out.write((tag.toString()).getBytes());
                    out.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
//
    }


}