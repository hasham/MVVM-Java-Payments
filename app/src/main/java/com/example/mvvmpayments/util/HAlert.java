package com.example.mvvmpayments.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.mvvmpayments.R;
import com.google.android.material.snackbar.Snackbar;

public class HAlert {

    public static void showAlertDialog(Activity context, String title, String message) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        alert.show();
    }

    public static void showAlertDialog(FragmentActivity context, String title, String message) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());
        alert.show();
    }

    public static void showAlertDialog(Activity context, String title, String message, DialogInterface.OnClickListener listener) {

        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setPositiveButton("OK", listener);
        alert.setCancelable(false);
        alert.show();
    }

    public static void showSnackBar(View view, String message) {
        //        findViewById(android.R.id.content) item_spinner_hint
        try {
            Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
            View snackBarView = snackbar.getView();
            TextView tv = (TextView) snackBarView.findViewById(R.id.snackbar_text);
            tv.setTextColor(Color.WHITE);
            snackbar.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showToast(Context context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Activity context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(FragmentActivity context, String message) {

        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Activity context, String message, int length) {

        Toast.makeText(context, message, length).show();
    }
}
