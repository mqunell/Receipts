package com.mattqunell.receipts;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class Add extends AppCompatActivity {
    DatePicker datepickerDate;
    EditText edittextPlace;
    EditText edittextAmount;
    RadioGroup radiogroupLayoutRadio;
    RadioButton radioCardOne;
    RadioButton radioCardTwo;
    RadioButton radioCardThree;

    private String dir;
    private String filenameNew;


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

        dir = getFilesDir().toString();
        filenameNew = "receipts.csv";
    }


    // add_button_submit
    public void addButtonSubmit(View v) {
        // Parse the date
        String date = String.valueOf(datepickerDate.getDayOfMonth());
        String month = String.valueOf(datepickerDate.getMonth());
        String year = String.valueOf(datepickerDate.getYear());
        String fullDate = month + "/" + date + "/" + year.substring(2);

        // Place and amount
        String place = edittextPlace.getText().toString();
        String amount = edittextAmount.getText().toString();

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

        // If all fields were filled out
        if (place.length() != 0 && amount.length() != 0 && cardNum != -1) {
            // If the receipt was appended successfully
            if (CsvManager.writeCsvFile(fullDate, place, amount, cardNum, dir, filenameNew)) {
                Toast.makeText(getApplicationContext(), "Receipt submitted successfully", Toast.LENGTH_SHORT).show();
                addButtonClear(v);
            }
            else {
                Toast.makeText(getApplicationContext(), "Receipt submission failed", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Receipt not submitted - missing info", Toast.LENGTH_SHORT).show();
        }
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
