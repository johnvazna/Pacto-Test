package com.johnvazna.pactotest.bases;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.johnvazna.pactotest.R;

@SuppressLint("InflateParams")
public class BaseActivity extends AppCompatActivity {

    private Dialog loadingDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Add more general for activities settings if necessary
    }

    protected void showLoading() {
        if (loadingDialog == null) {
            View view = LayoutInflater.from(this).inflate(R.layout.custom_progress_dialog, null);
            loadingDialog = new Dialog(this);
            loadingDialog.setContentView(view);
            loadingDialog.setCancelable(false);

            if (loadingDialog.getWindow() != null) {
                loadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                loadingDialog.getWindow().setGravity(Gravity.CENTER);
            }
        }
        loadingDialog.show();
    }

    protected void hideLoading() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }
}
