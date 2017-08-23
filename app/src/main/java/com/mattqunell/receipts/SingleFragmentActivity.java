package com.mattqunell.receipts;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/*
 * An abstract class with reusable Fragment-hosting code.
 */
public abstract class SingleFragmentActivity extends AppCompatActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = createFragment();

            /*
             * Transactions add, remove, attach, detach, and replace fragments.
             * This creates a new fragment transaction, includes one operation, and commits it.
             * Adding the Fragment to the FragmentManager calls its onAttach, onCreate, and
             * onCreateView methods.
             */
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}