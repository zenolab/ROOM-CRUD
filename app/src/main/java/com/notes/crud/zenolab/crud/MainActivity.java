package com.notes.crud.zenolab.crud;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private static final String LOG_TAG = new RuntimeException().getStackTrace()[0].getClassName();

    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                        addFragmentOnTop(new ScrollFragment());

            }
        });
    }

    private void addFragmentOnTop(Fragment fragment) {
        // получаем экземпляр FragmentTransaction
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        // добавляем фрагмент
        ScrollFragment myFragment = new ScrollFragment();
        fragmentTransaction.add(R.id.content_frame, myFragment);
        /**До добавления фрагмента. проверить, находится ли этот фрагмент уже в стеке */
        // .getSupportFragmentManager().findFragmentByTag(ScrollFragment.TAG);
       // .addToBackStack(FragmentName.TAG);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


class CustomViewHolder extends RecyclerView.ViewHolder {

    public final View mView;

    TextView txtTitle;
    TextView txtValue;
    TextView txtRound;
    TextView txtChange;
    private ImageView coverImage;

    CustomViewHolder(View itemView) {
        super(itemView);
        mView = itemView;


    }
}

