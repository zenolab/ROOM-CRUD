package com.notes.crud.zenolab.crud.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.notes.crud.zenolab.crud.R;

public class NoteHolder extends RecyclerView.ViewHolder    {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    TextView textViewTitle;
    TextView textViewContent;

    public NoteHolder(View itemView) {
        super(itemView);


        textViewTitle = itemView.findViewById(R.id.tv_title);
        textViewContent = itemView.findViewById(R.id.item_text);

    }
}
