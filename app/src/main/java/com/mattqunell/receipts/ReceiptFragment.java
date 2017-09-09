package com.mattqunell.receipts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ReceiptFragment extends Fragment {

    private Receipt mReceipt;

    // UI elements


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_receipt, container, false);

        //mReceipt = ReceiptBook.get().getReceipts().get(0);

        // UI elements


        return v;
    }
}
