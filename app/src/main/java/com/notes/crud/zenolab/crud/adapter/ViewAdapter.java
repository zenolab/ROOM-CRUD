package com.notes.crud.zenolab.crud.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.notes.crud.zenolab.crud.adapter.NoteHolder;
import com.notes.crud.zenolab.crud.room.entity.Note;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter <NoteHolder>{

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();


    private Context context;
    private List<String> notes;
    private List<Note> list;
    private LayoutInflater layoutInflater;
    private OnNoteItemClick onNoteItemClick;

    public ViewAdapter(Context context, List<Note> notes){
        this.context = context;

       // this.notes = notes;

        layoutInflater = LayoutInflater.from(context);
        this.list = list;
        this.context = context;
        this.onNoteItemClick = (OnNoteItemClick) context;
    }

    @Override
    public NoteHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return new NoteHolder(inflater, viewGroup);
    }

    @Override
    public void onBindViewHolder(NoteHolder noteHolder, int position) {
        noteHolder.title.setText(notes.get(position));

        Log.e(LOG_TAG, "onBindViewHolder: "+ list.get(position));
        noteHolder.textViewTitle.setText(list.get(position).getTitle());
        noteHolder.textViewContent.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() { return notes.size(); }
}


