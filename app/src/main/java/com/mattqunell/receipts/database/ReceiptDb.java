package com.mattqunell.receipts.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.DateFormat;

import com.mattqunell.receipts.R;
import com.mattqunell.receipts.data.Receipt;
import com.mattqunell.receipts.database.ReceiptDbSchema.ReceiptTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/*
 * ReceiptDb is implemented as a singleton, which is a class that allows only one instance of
 * itself to be created. It exists as long as the application is in memory, and is available through
 * lifecycle changes in activities and fragments. Singletons allow data to be easily passed between
 * controller classes, but should not be used for everything or as long-term storage solutions.
 */
public class ReceiptDb {

    // The one ReceiptDb to use throughout the app
    private static ReceiptDb sReceiptDb;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    // Static getter that creates sReceiptDb if it doesn't exist and returns it
    public static ReceiptDb get(Context context) {
        if (sReceiptDb == null) {
            sReceiptDb = new ReceiptDb(context);
        }

        return sReceiptDb;
    }

    // Private constructor to limit instantiation
    private ReceiptDb(Context context) {
        mContext = context;
        mDatabase = new ReceiptBaseHelper(mContext).getWritableDatabase();
    }

    // Adds a new Receipt to the database
    public void addReceipt(Receipt receipt) {
        ContentValues values = getContentValues(receipt);
        mDatabase.insert(ReceiptTable.NAME, null, values);
    }

    // Edits an existing Receipt in the database
    public void updateReceipt(Receipt receipt) {
        String uuidString = receipt.getId().toString();
        ContentValues values = getContentValues(receipt);

        mDatabase.update(ReceiptTable.NAME,
                values,
                ReceiptTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    // Gets a Receipt from a UUID
    public Receipt getReceipt(UUID receiptId) {
        List<Receipt> receipts = getReceipts();

        for (Receipt r : receipts) {
            if (r.getId().equals(receiptId)) {
                return r;
            }
        }

        // Should not reach this point
        return null;
    }

    // Gets an ArrayList of all Receipts in the database
    public List<Receipt> getReceipts() {
        List<Receipt> receipts = new ArrayList<>();

        ReceiptCursorWrapper cursor = queryReceipts(null, null);

        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                receipts.add(cursor.getReceipt());
                cursor.moveToNext();
            }
        }
        finally {
            cursor.close();
        }

        return receipts;
    }

    // Builds a formatted report of all Receipts in the database for exporting
    public String getReceiptReport() {

        // Get and sort the Receipts
        List<Receipt> receipts = getReceipts();
        Collections.sort(receipts);

        // The array of cards
        String[] cards = mContext.getResources().getStringArray(R.array.cards);

        // Find the max location and card lengths
        int maxLocationLen = 0;
        int maxCardLen = 0;
        for (Receipt r : receipts) {
            int locLen = r.getLocation().length();
            if (locLen > maxLocationLen) maxLocationLen = locLen;

            int cardLen = cards[r.getCard()].length();
            if (cardLen > maxCardLen) maxCardLen = cardLen;
        }

        // Build the output String
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < receipts.size(); i++) {
            Receipt r = receipts.get(i);

            // Date
            output.append(DateFormat.format("MM/dd", r.getDate()).toString());
            output.append("   ");

            // Location and spacing
            output.append(r.getLocation());
            for (int j = r.getLocation().length(); j < maxLocationLen; j++) {
                output.append(" ");
            }
            output.append("   ");

            // Card and spacing
            output.append(cards[r.getCard()]);
            for (int j = cards[r.getCard()].length(); j < maxCardLen; j++) {
                output.append(" ");
            }
            output.append("   ");

            // Amount
            output.append(r.getAmount());

            // ** if not paid out
            if (!r.wasPaid())
                output.append("**");

            // Extra newline if the next date is different
            if (i + 1 < receipts.size() && !r.getDate().equals(receipts.get(i+1).getDate()))
                output.append("\n");

            output.append("\n");
        }

        return output.toString();
    }

    // Removes a Receipt from the database
    public void removeReceipt(Receipt receipt) {
        String uuidString = receipt.getId().toString();

        mDatabase.delete(ReceiptTable.NAME,
                ReceiptTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }

    // Deletes all Receipts in the database
    public void removeAllReceipts() {
        mDatabase.delete(ReceiptTable.NAME, null, null);
    }

    // Helper method that essentially converts a Receipt into a ContentValues
    private static ContentValues getContentValues(Receipt receipt) {

        // ContentValues is a key-value class specifically designed for SQLite data
        ContentValues values = new ContentValues();

        values.put(ReceiptTable.Cols.UUID, receipt.getId().toString());
        values.put(ReceiptTable.Cols.LOCATION, receipt.getLocation());
        values.put(ReceiptTable.Cols.DATE, receipt.getDate().getTime());
        values.put(ReceiptTable.Cols.CARD, receipt.getCard());
        values.put(ReceiptTable.Cols.AMOUNT, receipt.getAmount());
        values.put(ReceiptTable.Cols.PAID, receipt.wasPaid() ? 1 : 0);

        return values;
    }

    // Helper method that searches for a Receipt (?)
    private ReceiptCursorWrapper queryReceipts(String whereClause, String[] whereArgs) {

        // Args: table, columns, where, whereArgs, groupBy, having, orderBy
        Cursor cursor = mDatabase.query(
                ReceiptTable.NAME,
                null, // null selects all
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ReceiptCursorWrapper(cursor);
    }
}
