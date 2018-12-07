package com.notes.crud.zenolab.crud;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.notes.crud.zenolab.crud.adapter.OnNoteItemClick;
import com.notes.crud.zenolab.crud.adapter.ViewAdapter;
import com.notes.crud.zenolab.crud.room.entity.Note;
import com.notes.crud.zenolab.crud.room.room_database.NoteDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollFragment extends Fragment  implements OnNoteItemClick {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private static final String TAG = "scroll_fragment";

   // private List<String> notes = new ArrayList<>(); //for test
    private List<Note> notes = new ArrayList<>();
    private RecyclerView recycler;
    private Context context;


    private TextView textViewMsg;
    private RecyclerView recyclerView;
    private NoteDatabase noteDatabase;
    private ViewAdapter notesAdapter;
    private int pos;


    private  View rootView;

    public ScrollFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData(){
       // for (int i = 0; i < 10; i++) { notes.add("" + i); } //for test
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return initViews(inflater.inflate(R.layout.scroll_main, container, false));

    }

    private View initViews(View view){
        context = getActivity();

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        //for crate home button
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(listener);

        rootView = view;
        recycler = view.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        notes = new ArrayList<>();
        recycler.setAdapter(new ViewAdapter(context, notes)); //also set data to adapter


        displayList();

        return view;
    }

    ///=============================================================================================

    private void displayList(){
        noteDatabase = NoteDatabase.getInstance(getActivity());
        new RetrieveTask(this).execute();
    }



    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

        }
    };



    @Override
    public void onNoteClick(final int pos) {

    }

    @Override
    public void onDestroy() {
        noteDatabase.cleanUp();
        super.onDestroy();
    }

    //==============================================================================================
    private static class RetrieveTask extends AsyncTask<Void,Void,List<Note>> {

        private WeakReference<ScrollFragment> activityReference;

        // only retain a weak reference to the activity
        RetrieveTask(ScrollFragment scrollFragment) {
            activityReference = new WeakReference<>(scrollFragment);
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            if (activityReference.get()!=null)
                return activityReference.get().noteDatabase.getNoteDao().getNotes();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            if (notes!=null && notes.size()>0 ){
                activityReference.get().notes.clear();
                activityReference.get().notes.addAll(notes);
                // hides empty text view
                activityReference.get().textViewMsg.setVisibility(View.GONE);
                activityReference.get().notesAdapter.notifyDataSetChanged();
            }
        }
    }
    ///=============================================================================================
}
