package com.example.rattaggingstudio;
import android.os.Bundle;
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
//                String[] info = {titleString, dateString};
                String info = titleString + ',' + dateString;
                view.clearFocus();
                System.out.println("title: " + titleString);
                System.out.println("date: " + dateString);
//                getParentFragmentManager().setFragmentResult("requestKey", result);
//                onSaveInstanceState("info", savedInstanceState, titleString, dateString);
                String title_key = "title";
                String date_key = "date";
                savedInstanceState.putCharSequence(title_key, titleString);
                savedInstanceState.putCharSequence(date_key, dateString);
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

    public void onSaveInstanceState(String key, Bundle outState, String title, String date) {
        super.onSaveInstanceState(outState);
        Bundle state = new Bundle();
        String title_key="title";
        state.putCharSequence(title_key, title);
        String date_key = "date";
        state.putCharSequence(date_key, date);
        /* If onDestroyView() is called first, we can use the previously savedState but we can't call saveState() anymore */
        /* If onSaveInstanceState() is called first, we don't have savedState, so we need to call saveState() */
        /* => (?:) operator inevitable! */
        outState.putBundle(key, state);
    }

}
