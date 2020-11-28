package com.example.rattaggingstudio;

import android.view.Gravity;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class Notification {

    public void showNotification(View view, String message){
        Snackbar snack = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackbarView = snack.getView();
        CoordinatorLayout.LayoutParams params =(CoordinatorLayout.LayoutParams) snackbarView.getLayoutParams();
        params.gravity = Gravity.TOP;
        snackbarView.setLayoutParams(params);
        snack.show();


    }
}
