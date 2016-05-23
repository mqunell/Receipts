package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.Calendar;

public class Add extends AppCompatActivity {
    DatePicker datepickerDate;
    EditText edittextPlace;
    EditText edittextAmount;
    RadioGroup radiogroupLayoutRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        datepickerDate = (DatePicker) findViewById(R.id.add_datepicker_date);
        edittextPlace = (EditText) findViewById(R.id.add_edittext_place);
        edittextAmount = (EditText) findViewById(R.id.add_edittext_amount);
        radiogroupLayoutRadio = (RadioGroup) findViewById(R.id.add_layout_radio);
    }


    // add_button_submit
    public void addButtonSubmit(View v) {
        /* TODO: Add/test these
         * Acquire the date from the DatePicker
         * Figure out which card RadioButton was selected
         * Create a helper class to manage writing to/reading the file; pass data to it
         */

        int date = datepickerDate.getDayOfMonth();
        int month = datepickerDate.getMonth();
        int year = datepickerDate.getYear();
        String fullDate = date + "/" + month + "/" + year;

        String place = edittextPlace.getText().toString();

        String amount = edittextAmount.getText().toString();

        // Find out which RadioButton is selected

        // Create a helper class to manage the file?
        // DataManager.write(fullDate, place, amount, card);

    }


    // add_button_clear
    public void addButtonClear(View v) {
        /* TODO: Add/test these
         * Set the DatePicker to the current date
         * Clear the EditTexts
         * Deselect radio button
         * Remove focus? (DatePicker is the first thing)
         */

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
