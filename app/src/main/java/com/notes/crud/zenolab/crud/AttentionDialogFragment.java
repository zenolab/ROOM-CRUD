package com.notes.crud.zenolab.crud;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class AttentionDialogFragment extends DialogFragment {

    private PredicateListener listener;


    public void registerCallBack(PredicateListener predicateListener) {
        listener = predicateListener;
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Сaution!")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Запись будет удалена безвозвратно!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        listener.predicateListener(true);
                    }

                })
                .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        listener.predicateListener(false);
                        dialog.dismiss();
                    }
                })
                .create();


    }


}
