package com.mattqunell.receipts;

import android.app.Activity;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mattqunell.receipts.data.Receipt;
import com.mattqunell.receipts.database.ReceiptDb;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.UUID;

public class ReceiptFragment extends Fragment {

    private Receipt mReceipt;

    // Tag for Bundle argument
    private static final String ARG_RECEIPT_ID = "receipt_id";

    // UI elements
    private EditText mLocation;
    private EditText mAmount;
    private Spinner mCardSpinner;
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
        mReceipt = ReceiptDb.get(getActivity()).getReceipt(receiptId);
    }

    /*
     * onCreateView handles both existing and new Receipt data. If the Receipt already exists, it
     * fills in the UI elements with the current data; if the Receipt does not exist, they are left
     * blank. Any changes are saved to the Receipt.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle inState) {
        final View v = inflater.inflate(R.layout.fragment_add_receipt, container, false);

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

        // Card Spinner
        mCardSpinner = v.findViewById(R.id.receipt_card);

        // Create adapter using string array and default spinner layout
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.cards, android.R.layout.simple_spinner_item);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mCardSpinner.setAdapter(spinnerAdapter);

        mCardSpinner.setSelection(mReceipt.getCard());
        mCardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mReceipt.setCard(position);
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Intentionally left blank
            }
        });

        // Paid Out Checkbox
        mPaidOut = v.findViewById(R.id.receipt_paid_out);
        mPaidOut.setChecked(mReceipt.wasPaid());
        mPaidOut.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReceipt.setPaid(isChecked);

                hideKeyboard();
            }
        });

        // Date CalendarView
        mDate = v.findViewById(R.id.receipt_date);
        mDate.setDate(mReceipt.getDate().getTime());
        mDate.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendar, int year, int month, int day) {
                Date selected = new GregorianCalendar(year, month, day).getTime();
                mReceipt.setDate(selected);

                hideKeyboard();
            }
        });

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        ReceiptDb.get(getActivity()).updateReceipt(mReceipt);
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
                ReceiptDb.get(getActivity()).removeReceipt(mReceipt);
                getActivity().finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // Helper method that hides the keyboard
    private void hideKeyboard() {
        /* todo: stop this from crashing the app
        InputMethodManager manager = (InputMethodManager) getActivity().
                getSystemService(Activity.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
        */
    }

    // Called from ReceiptActivity when Back is pressed
    protected void onBackPressed() {

        // If mLocation or mAmount is empty, make a Toast
        if (mLocation.getText().toString().equals("") || mAmount.getText().toString().equals("")) {
            Toast.makeText(getActivity(), R.string.incomplete_receipt, Toast.LENGTH_SHORT).show();
        }
        else {
            getActivity().finish();
        }
    }
}
