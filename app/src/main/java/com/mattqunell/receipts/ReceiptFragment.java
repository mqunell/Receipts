package com.mattqunell.receipts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Date;
import java.util.UUID;

public class ReceiptFragment extends Fragment {

    private Receipt mReceipt;

    // Tag for Bundle argument
    private static final String ARG_RECEIPT_ID = "receipt_id";

    // UI elements
    private EditText mLocation;
    private EditText mAmount;
    private RadioGroup mCardGroup;
    private CheckBox mPaidOut;
    private CalendarView mDate;

    // Encapsulates the implementation details of new instances of ReceiptFragment
    public static ReceiptFragment newInstance(UUID receiptId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_RECEIPT_ID, receiptId);

        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle inState) {
        super.onCreate(inState);
        setHasOptionsMenu(true);

        UUID receiptId = (UUID) getArguments().getSerializable(ARG_RECEIPT_ID);
        mReceipt = ReceiptBook.get(getActivity()).getReceipt(receiptId);
    }

    /*
     * onCreateView handles both existing and new Receipt data. If the Receipt already exists, it
     * fills in the UI elements with the current data; if the Receipt does not exist, they are left
     * blank. Any changes are saved to the Receipt.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle inState) {
        final View v = inflater.inflate(R.layout.fragment_receipt, container, false);

        // Location EditText
        mLocation = v.findViewById(R.id.receipt_location);
        mLocation.setText(mReceipt.getLocation());
        mLocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                mReceipt.setLocation(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank
            }
        });

        // Amount EditText
        mAmount = v.findViewById(R.id.receipt_amount);
        mAmount.setText(mReceipt.getAmount());
        mAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int after) {
                mReceipt.setAmount(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Intentionally left blank
            }
        });

        // Card RadioGroup
        mCardGroup = v.findViewById(R.id.receipt_card_group);
        RadioButton selected = (RadioButton) mCardGroup.getChildAt(mReceipt.getCard());
        selected.setChecked(true);
        mCardGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int selectedRadioId) {
                RadioButton newSelected = v.findViewById(mCardGroup.getCheckedRadioButtonId());
                mReceipt.setCard(mCardGroup.indexOfChild(newSelected));
            }
        });

        // Paid Out Checkbox
        mPaidOut = v.findViewById(R.id.receipt_paid_out);
        mPaidOut.setChecked(mReceipt.wasPaid());
        mPaidOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReceipt.setPaid(isChecked);
            }
        });

        // Date CalendarView
        mDate = v.findViewById(R.id.receipt_date);
        mDate.setDate(mReceipt.getDate().getTime());
        mDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int i, int i1, int i2) {
                mReceipt.setDate(new Date(calendarView.getDate()));
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        ReceiptBook.get(getActivity()).updateReceipt(mReceipt);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_receipt, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_receipt:
                ReceiptBook.get(getActivity()).removeReceipt(mReceipt);
                getActivity().finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
