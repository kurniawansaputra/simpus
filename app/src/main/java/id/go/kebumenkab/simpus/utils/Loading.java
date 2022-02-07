package id.go.kebumenkab.simpus.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;
import id.go.kebumenkab.simpus.R;

public class Loading {
    private final Activity activity;
    private ProgressDialog progressDialog;

    public Loading(Activity mActivity) {
        activity = mActivity;
    }

    public void startLoading() {
        progressDialog = new ProgressDialog(activity);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_loading);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//        LayoutInflater inflater = activity.getLayoutInflater();
//        builder.setView(inflater.inflate(R.layout.layout_loading, null));
//        builder.setCancelable(true);
//        alertDialog = builder.create();
//        alertDialog.show();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    public void dismissLoading() {
        progressDialog.dismiss();
    }
}
