package com.mattqunell.receipts;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/*
 * ReceiptListActivity extends SingleFragmentActivity (which handles all FragmentManager code), and
 * simply returns a new ReceiptListFragment.
 */
public class ReceiptListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new ReceiptListFragment();
    }

    private class ReceiptHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ReceiptHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_receipt, parent, false));
            itemView.setOnClickListener(this);
        }

        public void bind(Receipt receipt) {

        }

        @Override
        public void onClick(View view) {

        }
    }

    private class ReceiptAdapter extends RecyclerView.Adapter<ReceiptHolder> {

        @Override
        public ReceiptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(ReceiptHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
