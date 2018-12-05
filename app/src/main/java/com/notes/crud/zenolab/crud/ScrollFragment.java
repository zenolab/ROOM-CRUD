package com.notes.crud.zenolab.crud;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScrollFragment extends Fragment {

    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private static final String TAG = "scroll_fragment";

    public ScrollFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.scroll_main, container, false);
        return view;
    }
}
