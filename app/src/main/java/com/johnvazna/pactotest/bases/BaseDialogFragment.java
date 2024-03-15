package com.johnvazna.pactotest.bases;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.johnvazna.pactotest.R;

public class BaseDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        @SuppressLint("InflateParams") View view = inflater.inflate(R.layout.dialog_error_custom, null);

        Button btnAccept = view.findViewById(R.id.btnAccept);
        btnAccept.setOnClickListener(v -> {
            dismiss();
        });

        builder.setView(view);

        return builder.create();
    }
}