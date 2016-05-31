package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class CsvManager {
    // CSV file header, comma deliminator, new line separator
    private static final String HEADER = "Date,Place,Amount,Card";
    private static final String COMMA = ",";
    private static final String NEW_LINE = "\n";
    private static final String FILE_NAME = "receipts.csv";


    // Writes the given date, place, amount, and card number to the CSV file
    public static boolean writeCsvFile(String date, String place, String amount, int cardNum, String dir) {
        String fileLoc = dir + FILE_NAME;

        FileWriter fileWriter;

        try {
            // If the file already exists, append to it
            if (new File(fileLoc).exists()) {
                fileWriter = new FileWriter(fileLoc, true);
                System.out.println("Appending to existing");
            }
            // If the file doesn't already exist, create it with a header
            else {
                fileWriter = new FileWriter(fileLoc);
                fileWriter.append(HEADER);
                fileWriter.append(NEW_LINE);
                System.out.println("Writing new file");
            }

            // Append the receipt data with comma deliminators, followed by a new line
            fileWriter.append(date);
            fileWriter.append(COMMA);
            fileWriter.append(place);
            fileWriter.append(COMMA);
            fileWriter.append(amount);
            fileWriter.append(COMMA);
            fileWriter.append(Integer.toString(cardNum));
            fileWriter.append(NEW_LINE);

            // Flush and close fileWriter
            fileWriter.flush();
            fileWriter.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("writeCsvFile - Error in try/catch");
            e.printStackTrace();
            return false;
        }
    }


    // Returns and ArrayList of String arrays, which are the receipts
    public static ArrayList<String[]> readCsvFile(String dir) {
        String fileLoc = dir + FILE_NAME;

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
                    String[] data = line.split(COMMA);
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


    // Prints the receipts from the CSV file - ONLY FOR TESTING PURPOSES
    public static void printCsvFile(String dir) {
        ArrayList<String[]> receipts = readCsvFile(dir);

        // Print each receipt individually
        for (String[] s : receipts) {
            System.out.println(Arrays.toString(s));
        }
    }
}
