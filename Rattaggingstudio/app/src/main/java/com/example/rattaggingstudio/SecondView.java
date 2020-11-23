package com.example.rattaggingstudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
        EditText editTextNumber = (EditText) view.findViewById(R.id.editTextNumber);
        EditText editTextDescription = (EditText) view.findViewById(R.id.editTextDescription);
        Button addTag = (Button) view.findViewById(R.id.tag);
        addTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fileNumber = (String) editTextNumber.getText().toString();
                String description = (String) editTextDescription.getText().toString();
                sqlDataHelper.addDataToTagTable(fileNumber, description);
                view.clearFocus();
            }
        });


    }
}
