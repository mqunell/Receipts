package com.mattqunell.receipts;

import android.support.v4.app.Fragment;

/*
 * ReceiptListActivity extends SingleFragmentActivity (which handles all FragmentManager code), and
 * simply returns a new ReceiptListFragment.
 */
public class ReceiptListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        //return new ReceiptListFragment();
        return null;
    }
}
