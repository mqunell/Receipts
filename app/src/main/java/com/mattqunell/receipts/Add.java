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
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
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



        writeCsvFile();
        printCsvFile();
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



        File f = new File(getFilesDir() + "/receipts.csv");
        f.delete();
    }



    public void writeCsvFile() {
        String fileName = "receipts.csv";
        String header = "Date,Place,Amount,Card";
        String fileLoc = getFilesDir() + "/receipts.csv";

        FileWriter fileWriter;

        try {
            // If the file already exists, append to it
            if (new File(getFilesDir(), fileName).exists()) {
                fileWriter = new FileWriter(fileLoc, true);
            }
            // If the file doesn't already exist, create it with a header
            else {
                fileWriter = new FileWriter(fileLoc);
                fileWriter.append(header);
                fileWriter.append("\n");
            }

            // Append the receipt data with comma deliminators, followed by a new line
            fileWriter.append("test one");
            fileWriter.append(",");
            fileWriter.append("test two");
            fileWriter.append(",");
            fileWriter.append("test three");
            fileWriter.append("\n");

            // Flush and close fileWriter
            System.out.println("writeCsvFile - CSV file created/appended successfully");
            fileWriter.flush();
            fileWriter.close();
        }
        catch (Exception e) {
            System.out.println("writeCsvFile - Error in try/catch");
            e.printStackTrace();
        }
    }

    public ArrayList<String[]> readCsvFile() {
        String fileLoc = getFilesDir() + "/receipts.csv";

        BufferedReader fileReader;

        // Create an ArrayList of String arrays to be created from the CSV file
        ArrayList<String[]> receipts = new ArrayList<>();

        // String to be used for each line of data in the CSV file
        String line;

        try {
            if (new File(fileLoc).exists()) {
                // Create the BufferedReader
                fileReader = new BufferedReader(new FileReader(fileLoc));

                // Skip the header, then read the file line by line
                fileReader.readLine();
                while ((line = fileReader.readLine()) != null) {
                    String[] data = line.split(",");
                    receipts.add(data);
                }

                // Close fileReader
                fileReader.close();
            } else {
                System.out.println("readCsvFile - No file found to read");
            }
        }
        catch (Exception e) {
            System.out.println("readCsvFile - Error in try/catch");
            e.printStackTrace();
        }

        return receipts;
    }

    public void printCsvFile() {
        ArrayList<String[]> receipts = readCsvFile();

        // Print each receipt individually
        for (String[] s : receipts) {
            System.out.println(Arrays.toString(s));
        }
    }
}
