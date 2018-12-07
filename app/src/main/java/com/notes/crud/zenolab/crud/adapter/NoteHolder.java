package com.notes.crud.zenolab.crud.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notes.crud.zenolab.crud.R;

public class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    TextView textViewContent;
    TextView textViewTitle;

    private OnNoteItemClick onNoteItemClick;

    TextView title;
    public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_note, parent, false));
        title = itemView.findViewById(R.id.title_tv);

       // super(itemView);
        itemView.setOnClickListener(this);
        textViewContent = itemView.findViewById(R.id.item_text);
        textViewTitle = itemView.findViewById(R.id.tv_title);

    }

    @Override
    public void onClick(View view) {
        onNoteItemClick.onNoteClick(getAdapterPosition());
    }
}
