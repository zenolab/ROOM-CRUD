package com.notes.crud.zenolab.crud;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class NoteHolder extends RecyclerView.ViewHolder {
    TextView title;
    public NoteHolder(LayoutInflater inflater, ViewGroup parent) {
        super(inflater.inflate(R.layout.item_note, parent, false));
        title = itemView.findViewById(R.id.title_tv);
    }
}
