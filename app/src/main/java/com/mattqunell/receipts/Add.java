package com.mattqunell.receipts;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Scanner;

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

        createFile();
        readFile();
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


    // Helper files - MOVE TO A NEW CLASS ONCE WORKING PROPERLY
    public void createFile() {
        String fileName = "receipts.csv";
        String header = "Date,Place,Amount,Card";
        FileOutputStream outputStream;

        if (!new File(getFilesDir(), fileName).exists()) {
            try {
                outputStream = openFileOutput(fileName, MODE_PRIVATE);
                outputStream.write(header.getBytes());
                outputStream.close();
                System.out.println("File created");
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File creation error");
            }
        }
        else {
            System.out.println("File not created; already exists");
            try {
                outputStream = openFileOutput(fileName, MODE_APPEND);
                outputStream.write("\ntest".getBytes());
                outputStream.close();
                System.out.println("File appended?");
            }
            catch (Exception e) {
                e.printStackTrace();
                System.out.println("File append error");
            }
        }
    }


    public void readFile() {
        try {
            String text;

            FileInputStream fileInputStream = openFileInput("receipts.csv");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }
            System.out.println(stringBuilder);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
