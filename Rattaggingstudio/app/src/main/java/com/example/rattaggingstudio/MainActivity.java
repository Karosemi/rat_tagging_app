package com.example.rattaggingstudio;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

public class MainActivity extends AppCompatActivity {
    public static SqlDataHelper sqlDataHelper;

    private static String handleEmptyStrings(String text){
        if (text.isEmpty()){
            text = "NaN";
        }
        return text;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sqlDataHelper = new SqlDataHelper(this);
        int oldVersion = 0;
        int newVersion = 1;
        sqlDataHelper.onUpgrade(sqlDataHelper.getWritableDatabase(), oldVersion, newVersion);
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
                String fileName = "data.csv";
                infoCursor.moveToFirst();
                StringBuilder tag = new StringBuilder();
                String title = infoCursor.getString(indexTitle);
                String date = infoCursor.getString(indexDate);
                tag.append("Title,Date\n" + title + "," + date);
                tag.append("\nFileNumber,Description");
                tagCursor.moveToFirst();
                int count = tagCursor.getCount();
//                if (count == 1) {
//                    String description = tagCursor.getString(indexDescription);
//                    if (description.isEmpty()){
//                        description = "NaN";
//                    }
//                    String fileNumber = tagCursor.getString(indexFileNumber);
//                    tag.append("\n" + fileNumber + "," + description);
//                }
//                else if (count > 1){
                for (int i = 0; i < count; i += 1) {
                    String description = tagCursor.getString(indexDescription);
                    if (description.isEmpty()){
                        description = "NaN";
                    }
                    String fileNumber = tagCursor.getString(indexFileNumber);
                    tag.append("\n" + fileNumber + "," + description);
                    tagCursor.moveToNext();
                }
//            }

                try {
                    FileOutputStream out = openFileOutput(fileName, Context.MODE_PRIVATE);
                    out.write((tag.toString()).getBytes());
                    out.close();
                    Context context = getApplicationContext();
                    File filelocation = new File(getFilesDir(), fileName);
                    Uri path = FileProvider.getUriForFile(context, "com.example.rattaggingstudio.fileprovider", filelocation);
                    Intent fileIntent = new Intent(Intent.ACTION_SEND);
                    fileIntent.setType("text/csv");
                    fileIntent.putExtra(Intent.EXTRA_SUBJECT, "Data");
                    fileIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    fileIntent.putExtra(Intent.EXTRA_STREAM, path);
                    startActivity(Intent.createChooser(fileIntent, "Send data"));

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