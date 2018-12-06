package com.notes.crud.zenolab.crud;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollFragment extends Fragment {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private static final String TAG = "scroll_fragment";

    private List<String> notes = new ArrayList<>();
    private RecyclerView recycler;
    private Context context;

    public ScrollFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData(){
        for (int i = 0; i < 10; i++) { notes.add("" + i); } //for test
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initViews(inflater.inflate(R.layout.scroll_main, container, false));
    }

    private View initViews(View v){
        context = getActivity();
        recycler = v.findViewById(R.id.recyclerView);
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(new ViewAdapter(context, notes)); //also set data to adapter
        return v;
    }
}
