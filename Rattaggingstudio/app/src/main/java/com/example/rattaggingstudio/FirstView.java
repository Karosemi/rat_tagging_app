package com.example.rattaggingstudio;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Context;

import com.opencsv.CSVWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstView extends Fragment {
    OpenCSVWriter csv;
    StringBuffer filePath;
    File file;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.first_view, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CSVWriter writer = null;
        String fileName = "./info.csv";
        EditText  addTitle = (EditText) view.findViewById(R.id.addTitle);
        EditText  editDate = (EditText) view.findViewById(R.id.editDate);
        Button next = (Button) view.findViewById(R.id.next);
//        filePath = new StringBuffer();
//        filePath.append('.');
//        file = new File(filePath.toString());
//        csv = new OpenCSVWriter(file);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleString = (String) addTitle.getText().toString();
                String dateString = (String) editDate.getText().toString();
//                String[] info = {titleString, dateString};
                String info = titleString + ',' + dateString;
                view.clearFocus();
                String title_key = "title";
                String date_key = "date";

                System.out.println("title: " + titleString);
                System.out.println("date: " + dateString);
                Info thisInfo = (Info) new Info(titleString, dateString);
                NavHostFragment.findNavController(FirstView.this)
                        .navigate(R.id.action_firstView_to_secondView);
            }

        }

        );



    }
//    private Bundle saveState(String title, String date) { /* called either from onDestroyView() or onSaveInstanceState() */
//        Bundle state = new Bundle();
//        state.putCharSequence(title, date);
//        return state;
//    }


}
