package com.notes.crud.zenolab.crud;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter <NoteHolder>{

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private List<String> notes;
    private Context context;
    ViewAdapter(Context context, List<String> notes){
        this.context = context;
        this.notes = notes;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new NoteHolder(inflater, viewGroup);
    }

    @Override
    public void onBindViewHolder(NoteHolder noteHolder, int i) {
        noteHolder.title.setText(notes.get(i));
    }

    @Override
    public int getItemCount() { return notes.size(); }
}


