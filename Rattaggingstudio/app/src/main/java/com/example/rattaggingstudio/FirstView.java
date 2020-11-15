package com.example.rattaggingstudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.opencsv.CSVWriter;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstView extends Fragment {

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
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleString = (String) addTitle.getText().toString();
                String dateString = (String) editDate.getText().toString();
                String[] info = {titleString, dateString};
                view.clearFocus();
                File file = new File(fileName);
                try {
                    FileWriter outputfile = new FileWriter(file);
                    CSVWriter writer = new CSVWriter(outputfile);
                    writer.writeNext(info);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("title: " + titleString);
                System.out.println("date: " + dateString);


                NavHostFragment.findNavController(FirstView.this)
                        .navigate(R.id.action_firstView_to_secondView);
            }
        });
    }

}
