package com.notes.crud.zenolab.crud.adapter;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notes.crud.zenolab.crud.R;
import com.notes.crud.zenolab.crud.room.entity.Note;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter <NoteHolder> {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private Context context;
    private List<Note> notes;
    private List<Note> list;
    private LayoutInflater layoutInflater;

    public ViewAdapter(Context context, List<Note> notes){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.notes = notes;
        this.list = notes;
    }
    @Override
    public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType ){
        View view = layoutInflater.inflate(R.layout.note_list_item,parent,false);
        return new NoteHolder(view);
    }
    @Override
    public void onBindViewHolder(NoteHolder noteHolder, int position) {
        Log.e(LOG_TAG, "---- onBindViewHolder: "+ list.get(position));
        noteHolder.textViewTitle.setText(list.get(position).getTitle());
        noteHolder.textViewContent.setText(list.get(position).getContent() );
        noteHolder.textViewContent.setSelected(true);//auto scroll
    }
    @Override
    public int getItemCount() { return notes.size(); }
}


