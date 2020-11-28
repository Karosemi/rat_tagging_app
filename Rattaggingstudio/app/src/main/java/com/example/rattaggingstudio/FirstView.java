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
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import com.google.android.material.snackbar.Snackbar;
import static com.example.rattaggingstudio.MainActivity.sqlDataHelper;

public class FirstView extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.first_view, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        EditText addTitle = (EditText) view.findViewById(R.id.addTitle);
        EditText editDate = (EditText) view.findViewById(R.id.editDate);
        editDate.setText(dtf.format(now));
        Button next = (Button) view.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String titleString = (String) addTitle.getText().toString();
                                        String dateString = (String) editDate.getText().toString();
                                        if (titleString.isEmpty()){
                                            Snackbar.make(view, "Add any title!", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                            return;
                                        }
                                        sqlDataHelper.addDataToInfoTable(titleString, dateString);
                                        view.clearFocus();
                                        NavHostFragment.findNavController(FirstView.this)
                                                .navigate(R.id.action_firstView_to_secondView);
                                    }

                                }

        );
    }
}
