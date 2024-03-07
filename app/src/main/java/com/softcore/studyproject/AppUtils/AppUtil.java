package com.softcore.studyproject.AppUtils;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class AppUtil {
    public static void showTost(Context context, String message) {

        Toast.makeText(context, "" + message, Toast.LENGTH_LONG).show();
    }
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(View view, String message) {
        progressDialog = new ProgressDialog(view.getContext());
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
