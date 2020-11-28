package com.example.rattaggingstudio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.material.snackbar.Snackbar;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import static com.example.rattaggingstudio.MainActivity.sqlDataHelper;

public class SecondView extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState

    ) {
        return inflater.inflate(R.layout.second_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CheckBox ratNamesCheckBoxes[];
        int namesNumber = 5;
        ratNamesCheckBoxes = new CheckBox[namesNumber];
        EditText editTextNumber = (EditText) view.findViewById(R.id.editTextNumber);
        EditText editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        ratNamesCheckBoxes[0] = (CheckBox) view.findViewById(R.id.checkBoxCoco);
        ratNamesCheckBoxes[1] = (CheckBox) view.findViewById(R.id.checkBoxXenia);
        ratNamesCheckBoxes[2] = (CheckBox) view.findViewById(R.id.checkBoxMiya);
        ratNamesCheckBoxes[3] = (CheckBox) view.findViewById(R.id.checkBoxMimi);
        ratNamesCheckBoxes[4] = (CheckBox) view.findViewById(R.id.checkBoxMiki);
        CheckBox checkBoxOther = (CheckBox) view.findViewById(R.id.checkBoxOther);
        TextView ratName = (TextView) view.findViewById(R.id.ratName);
        EditText textRatName = (EditText) view.findViewById(R.id.textRatName);
        Spinner emotionSpinner = (Spinner) view.findViewById(R.id.spinner);
        Button clear = (Button) view.findViewById(R.id.clear);
        Context context = getActivity();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context,
                R.array.emotion_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        emotionSpinner.setAdapter(adapter);
        Button addTag = (Button) view.findViewById(R.id.tag);
        editTextNumber.setText("1");
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                editTextDescription.setText("");
            }
        });
        checkBoxOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (checkBoxOther.isChecked()){
                ratName.setVisibility(View.VISIBLE);
                textRatName.setVisibility(View.VISIBLE);}
                else {
                    ratName.setVisibility(View.INVISIBLE);
                    textRatName.setVisibility(View.INVISIBLE);
                }
            }
        });

        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fileNumber = (String) editTextNumber.getText().toString();
                String description = (String) editTextDescription.getText().toString();
                String emotion = (String)  emotionSpinner.getSelectedItem().toString();
                StringBuilder ratNames = new StringBuilder();
                for (int i = 0; i < namesNumber; i++) {
                    String ratName = getRatName(ratNamesCheckBoxes[i]);
                    if (!ratName.isEmpty()) {
                        ratNames.append(ratName + "_");
                    }
                }
                String ratNameString = textRatName.getText().toString();
                ratNames.append(ratNameString);
                String ratNamesString = ratNames.toString();
                if (ratNamesString.isEmpty()){
                    Snackbar.make(view, "Rat names are not choosen!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                else if (description.isEmpty()){
                    Snackbar.make(view, "Add description!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    return;
                }
                sqlDataHelper.addDataToTagTable(fileNumber, description, ratNamesString, emotion);
                int i = Integer.parseInt(fileNumber);
                i += 1;
                String newFileNumber = String.valueOf(i);
                editTextNumber.setText(newFileNumber);
                view.clearFocus();
            }
        });
    }

    private static String getRatName(CheckBox checkbox) {
        if (checkbox.isChecked()) {
            String ratName = checkbox.getText().toString();
            System.out.println("Rat is added.");
            return ratName;

        } else {
            String ratName = "";
            return ratName;
        }


    }


}

