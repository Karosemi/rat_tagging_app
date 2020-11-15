package com.example.rattaggingstudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

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
        Button save = (Button) view.findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.clearFocus();
                NavHostFragment.findNavController(SecondView.this)
                        .navigate(R.id.action_secondView_to_firstView);
            }
        });

    }
}
