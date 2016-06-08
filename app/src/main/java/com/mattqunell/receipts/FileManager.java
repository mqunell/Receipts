package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

public class FileManager {

    // Writes the given date, place, amount, and card number to the .txt file
    public static boolean writeFile(String receipt, String dir, String fileName) {
        String fileLoc = dir + fileName;

        FileWriter fileWriter;

        try {
            // If the file already exists, append to it
            if (new File(fileLoc).exists()) {
                fileWriter = new FileWriter(fileLoc, true);
                //TESTING:
                System.out.println("Appending to existing");
            }
            // If the file doesn't already exist, create it with a header
            else {
                fileWriter = new FileWriter(fileLoc);
                //TESTING:
                System.out.println("Writing new file");
            }

            // Append the receipt data with comma deliminators, followed by a new line
            fileWriter.append(receipt);
            fileWriter.append("\n");

            // Flush and close fileWriter
            fileWriter.flush();
            fileWriter.close();
            return true;
        }
        catch (Exception e) {
            System.out.println("writeFile - Error in try/catch");
            e.printStackTrace();
            return false;
        }
    }


    // Returns and ArrayList of String arrays, which are the receipts
    public static ArrayList<String> readFile(String dir, String fileName) {
        String fileLoc = dir + fileName;

        BufferedReader fileReader;

        // Create an ArrayList of String arrays to be created from the .txt file
        ArrayList<String> receipts = new ArrayList<>();

        // String to be used for each line of data in the .txt file
        String line;

        try {
            if (new File(fileLoc).exists()) {
                // Create the BufferedReader
                fileReader = new BufferedReader(new FileReader(fileLoc));

                // Read the file line by line
                while ((line = fileReader.readLine()) != null) {
                    receipts.add(line);
                }

                // Close fileReader
                fileReader.close();
            } else {
                System.out.println("readFile - No file found to read");
            }
        }
        catch (Exception e) {
            System.out.println("readFile - Error in try/catch");
            e.printStackTrace();
        }

        return receipts;
    }


    // Move receipts from NewReceipts to Archived
    public static void archiveReceipts(String dir) {
        ArrayList<String> receipts = readFile(dir, "new_receipts.txt");

        for (String receipt : receipts) {
            writeFile(receipt, dir, "archived_receipts.txt");
        }

        clearReceipts(dir, "new_receipts.txt");
    }


    // Clear the receipts from the specified file
    // TODO: Refresh the activity on clear, use the .delete() boolean for success/fail Toasts
    public static void clearReceipts(String dir, String fileName) {
        String fileLoc = dir + fileName;
        File f = new File(fileLoc);
        f.delete();
    }


    //TESTING: Prints the receipts from the .txt file
    public static void printFile(String dir, String fileName) {
        ArrayList<String> receipts = readFile(dir, fileName);

        // Print each receipt individually
        for (String s : receipts) {
            System.out.println(s);
        }
    }
}
