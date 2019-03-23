package com.notes.crud.zenolab.crud;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.Nullable;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.fragment.app.Fragment;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.notes.crud.zenolab.crud.adapter.RecyclerTouchListener;
import com.notes.crud.zenolab.crud.adapter.RecyclerViewClickListener;
import com.notes.crud.zenolab.crud.adapter.NoteAdapter;
import com.notes.crud.zenolab.crud.room.entity.Note;
import com.notes.crud.zenolab.crud.room.room_database.NoteDatabase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollFragment extends Fragment {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private List<Note> notes = new ArrayList<>();
    private RecyclerView recycler;
    private Context context;

    private TextView textViewMsg;
    private NoteDatabase noteDatabase;
    private NoteAdapter notesAdapter;
    FloatingActionButton fab;
    private int pos;
    AttentionDialogFragment attentionDialogFragment;


    private View rootView;

    public ScrollFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews(inflater.inflate(R.layout.scroll_main, container, false));
    }

    private void displayList() {
        noteDatabase = NoteDatabase.getInstance(getActivity());
        new RetrieveTask(this).execute();
    }


    private View initViews(View view) {
        context = view.getContext();
        textViewMsg = (TextView) view.findViewById(R.id.tv__empty);

        fab = ((FloatingActionButton) getActivity().findViewById(R.id.fab_center));
        fab.setOnClickListener(listener);

        recycler = view.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        notes = new ArrayList<>();
        notesAdapter = new NoteAdapter(context, notes);
        initListClickListener(recycler);
        recycler.setAdapter(notesAdapter);
        displayList();

        return view;
    }

    private void initListClickListener(RecyclerView recyclerView) {
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, final int position) {
                startActivity(new Intent(getActivity(), AddNoteActivity.class).
                        putExtra("note", notes.get(position)));
            }

            @Override
            public void onLongClick(View view, int position) {
                action(position);
            }
        }));
    }

    private void action(final int position) {

        new AlertDialog.Builder(getContext())
                .setTitle("Select Options")
                .setItems(new String[]{"Delete", "Update"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                showDialogFragment(position);
                                break;
                            case 1:
                                try {
                                    getActivity().startActivityForResult(
                                            new Intent(getActivity(),
                                                    AddNoteActivity.class)
                                                    .putExtra("note", notes.get(position)),
                                            100);
                                    break;
                                } catch (NullPointerException npe) {
                                    Toast.makeText(getActivity(),
                                            "Screen loading error", Toast.LENGTH_SHORT).show();
                                }
                        }
                    }
                }).show();
    }


    public void showDialogFragment(int position) {

        attentionDialogFragment = new AttentionDialogFragment();
        attentionDialogFragment.registerCallBack(new PredicateListener() {
            @Override
            public void predicateListener(boolean arg) {
                if (arg) {
                    noteDatabase.getNoteDao().deleteNote(notes.get(position));
                    notes.remove(position);
                    listVisibility();
                }
            }
        });
        attentionDialogFragment.show(getFragmentManager(), "custom");
    }

    private View.OnClickListener listener = view -> {
        startActivity(new Intent(getActivity(), AddNoteActivity.class));
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100 && resultCode > 0) {
            if (resultCode == 1) {
                notes.add((Note) data.getSerializableExtra("note"));
            } else if (resultCode == 2) {
                notes.set(pos, (Note) data.getSerializableExtra("note"));
            }
            listVisibility();
        }
    }

    private void listVisibility() {
        int emptyMsgVisibility = View.GONE;
        if (notes.size() == 0) { // no item to display
            if (textViewMsg.getVisibility() == View.GONE)
                emptyMsgVisibility = View.VISIBLE;
        }
        textViewMsg.setVisibility(emptyMsgVisibility);
        notesAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        displayList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        noteDatabase.cleanUp();

    }


    private static class RetrieveTask extends AsyncTask<Void, Void, List<Note>> {

        private WeakReference<ScrollFragment> scrollFragmentWeakReference;

        // only retain a weak reference to the activity
        RetrieveTask(ScrollFragment scrollFragment) {
            scrollFragmentWeakReference = new WeakReference<>(scrollFragment);
        }

        @Override
        protected List<Note> doInBackground(Void... voids) {
            if (scrollFragmentWeakReference.get() != null)
                return scrollFragmentWeakReference.get().noteDatabase.getNoteDao().getNotes();
            else
                return null;
        }

        @Override
        protected void onPostExecute(List<Note> notes) {
            if (notes != null && notes.size() > 0) {
                scrollFragmentWeakReference.get().notes.clear();
                scrollFragmentWeakReference.get().notes.addAll(notes);
                // hides empty text view
                scrollFragmentWeakReference.get().textViewMsg.setVisibility(View.GONE);
                scrollFragmentWeakReference.get().notesAdapter.notifyDataSetChanged();
                Log.d(LOG_TAG, "...");
            }
        }
    }

}
