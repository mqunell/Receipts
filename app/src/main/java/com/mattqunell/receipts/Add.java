package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.Calendar;

public class Add extends AppCompatActivity {
    DatePicker datepickerDate;
    EditText edittextPlace;
    EditText edittextAmount;
    RadioGroup radiogroupLayoutRadio;
    RadioButton radioCardOne;
    RadioButton radioCardTwo;
    RadioButton radioCardThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        datepickerDate = (DatePicker) findViewById(R.id.add_datepicker_date);
        edittextPlace = (EditText) findViewById(R.id.add_edittext_place);
        edittextAmount = (EditText) findViewById(R.id.add_edittext_amount);
        radiogroupLayoutRadio = (RadioGroup) findViewById(R.id.add_layout_radio);
        radioCardOne = (RadioButton) findViewById(R.id.add_radio_cardOne);
        radioCardTwo = (RadioButton) findViewById(R.id.add_radio_cardTwo);
        radioCardThree = (RadioButton) findViewById(R.id.add_radio_cardThree);
    }


    // add_button_submit
    public void addButtonSubmit(View v) {
        /* TODO: Add/test these
         * Figure out which card RadioButton was selected
         * Create a helper class to manage writing to/reading the file; pass data to it
         */

        int date = datepickerDate.getDayOfMonth();
        int month = datepickerDate.getMonth();
        int year = datepickerDate.getYear();
        String fullDate = date + "/" + month + "/" + year;

        String place = edittextPlace.getText().toString();

        String amount = edittextAmount.getText().toString();

        //TODO: Test radiogroupLayoutRadio.getCheckedRadioButtonId()
        // Find out which RadioButton is selected
        int cardNum;

        if (radioCardOne.isChecked()) {
            cardNum = 1;
        }
        else if (radioCardTwo.isChecked()) {
            cardNum = 2;
        }
        else if (radioCardThree.isChecked()) {
            cardNum = 3;
        }
        else {
            cardNum = -1;
        }

        // Create a helper class to manage the file?
        // DataManager.write(fullDate, place, amount, card);

        /*
         * TODO: Test CvsManager here first; try saving a file to Downloads or something, to be able
         * TODO: to look at it while appending data and moving data from New->Old. If it works, then
         * TODO: create the Java class to use as a helper
         */
    }


    // add_button_clear
    public void addButtonClear(View v) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int date = cal.get(Calendar.DAY_OF_MONTH);
        datepickerDate.updateDate(year, month, date);

        edittextPlace.setText("");
        edittextPlace.requestFocus();

        edittextAmount.setText("");

        radiogroupLayoutRadio.clearCheck();
    }
}
