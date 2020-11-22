package com.example.rattaggingstudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import static com.example.rattaggingstudio.MainActivity.sqlDataHelper;

public class SecondView extends Fragment {
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.second_view, container, false);
    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText editTextNumber = (EditText) view.findViewById(R.id.editTextNumber);
        EditText  editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        Button addTag = (Button) view.findViewById(R.id.tag);
//        Button save = (Button) view.findViewById(R.id.save);
        Measurement thisMeasurement = (Measurement) new Measurement();
        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileNumber = (String) editTextNumber.getText().toString();
                System.out.println(fileNumber);
                String description = (String) editTextDescription.getText().toString();
//                Measurement thisMeasurement = (Measurement) new Measurement(fileNumber, description);
                thisMeasurement.addDescription(description);
                thisMeasurement.addFileNumber(fileNumber);
                boolean insertInfo = sqlDataHelper.addDataToTagTable(fileNumber, description);
                ArrayList<String> listDescription = (ArrayList<String>) thisMeasurement.getDescription();
                ArrayList<String> listFileNumber= (ArrayList<String>) thisMeasurement.getFileNumber();
                for (String s : listFileNumber) {
                    System.out.println("File Number:" + s);
                }
                for (String k : listDescription) {
                    System.out.println("File Description:" + k);
                }
                view.clearFocus();
            }
        });

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                view.clearFocus();
//                NavHostFragment.findNavController(SecondView.this)
//                        .navigate(R.id.action_secondView_to_firstView);
//            }
//        });

    }
}
