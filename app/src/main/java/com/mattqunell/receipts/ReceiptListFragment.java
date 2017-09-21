package com.mattqunell.receipts;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ReceiptListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ReceiptAdapter mAdapter;

    @Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle inState) {

        // Inflate the layout file
        View view = inflater.inflate(R.layout.fragment_receipt_list, container, false);

        mRecyclerView = view.findViewById(R.id.receipt_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUi();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUi();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_receipt_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_receipt:

                // Create a new Receipt, add it to ReceiptBook, and start it
                Receipt receipt = new Receipt();
                ReceiptBook.get(getActivity()).addReceipt(receipt);
                startReceiptActivity(receipt);

                return true;

            case R.id.remove_all_receipts:

                // If there are Receipts to remove
                if (ReceiptBook.get(getActivity()).getReceipts().size() > 0) {

                    // AlertDialog for removal confirmation
                    new AlertDialog.Builder(getActivity())
                            .setIcon(R.drawable.ic_alert_warning)
                            .setTitle(R.string.remove_all_receipts)
                            .setMessage(R.string.remove_all_confirmation)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    // Clear the database, make a Toast, and update the UI
                                    ReceiptBook.get(getActivity()).removeAllReceipts();
                                    Toast.makeText(getActivity(), R.string.removed_all_receipts,
                                            Toast.LENGTH_SHORT).show();
                                    updateUi();

                                }
                            })
                            .setNegativeButton("No", null)
                            .show();
                }
                else {
                    Toast.makeText(getActivity(), R.string.no_receipts, Toast.LENGTH_SHORT).show();
                }

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Helper method that creates and sets/updates the Adapter
    private void updateUi() {

        // Get the list of Receipts
        List<Receipt> receipts = ReceiptBook.get(getActivity()).getReceipts();

        // Create the adapter, or refresh the existing one
        if (mAdapter == null) {
            mAdapter = new ReceiptAdapter(receipts);
            mRecyclerView.setAdapter(mAdapter);
        }
        else {
            mAdapter.setReceipts(receipts);
            mAdapter.notifyDataSetChanged();
        }
    }

    // Helper method to start a ReceiptActivity/ReceiptFragment at a specific Receipt
    private void startReceiptActivity(Receipt receipt) {
        startActivity(ReceiptActivity.newIntent(getActivity(), receipt.getId()));
    }


    /*
     * ReceiptHolder: The ViewHolder
     * Inflates and owns each individual layout (list_item_receipt) within the RecyclerView.
     * The bind(Receipt) method is called each time a new Receipt should be displayed.
     */
    private class ReceiptHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Receipt mReceipt;

        private TextView mLocation;
        private TextView mDate;
        private TextView mAmount;
        private TextView mCard;

        public ReceiptHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_receipt, parent, false));
            itemView.setOnClickListener(this);

            // UI elements
            mLocation = itemView.findViewById(R.id.receipt_location);
            mDate = itemView.findViewById(R.id.receipt_date);
            mAmount = itemView.findViewById(R.id.receipt_amount);
            mCard = itemView.findViewById(R.id.receipt_card);
        }

        public void bind(Receipt receipt) {
            mReceipt = receipt;

            mLocation.setText(mReceipt.getLocation());
            mDate.setText(DateFormat.format("M/d", mReceipt.getDate()));
            mAmount.setText(mReceipt.getAmount());
            mAmount.setTextColor(mReceipt.wasPaid() ? Color.RED : Color.GREEN);
            String[] cards = getResources().getStringArray(R.array.cards);
            mCard.setText(cards[mReceipt.getCard()]);
        }

        @Override
        public void onClick(View view) {
            startReceiptActivity(mReceipt);
        }
    }


    /*
     * ReceiptAdapter: The Adapter
     * Connects the ViewHolder and Receipts by knowing how Receipts and ReceiptBook are implemented.
     * The overridden methods are all required and called by the RecyclerView itself.
     */
    private class ReceiptAdapter extends RecyclerView.Adapter<ReceiptHolder> {

        private List<Receipt> mReceipts;

        public ReceiptAdapter(List<Receipt> receipts) {
            mReceipts = receipts;
        }

        @Override
        public ReceiptHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ReceiptHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ReceiptHolder holder, int position) {
            Receipt receipt = mReceipts.get(position);
            holder.bind(receipt);
        }

        @Override
        public int getItemCount() {
            return mReceipts.size();
        }

        public void setReceipts(List<Receipt> receipts) {
            mReceipts = receipts;
        }
    }
}
