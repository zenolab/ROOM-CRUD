package com.notes.crud.zenolab.crud.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.notes.crud.zenolab.crud.R;

public class NoteHolder extends RecyclerView.ViewHolder {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    TextView textViewTitle, textViewContent, textViewId, textViewDate;

    public NoteHolder(View itemView) {
        super(itemView);
        textViewId = itemView.findViewById(R.id.tv_id_note);
        textViewTitle = itemView.findViewById(R.id.tv_title);
        textViewContent = itemView.findViewById(R.id.item_text);
        textViewDate = itemView.findViewById(R.id.tv_date);

    }
}
