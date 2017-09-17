package com.mattqunell.receipts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mattqunell.receipts.database.ReceiptBaseHelper;
import com.mattqunell.receipts.database.ReceiptCursorWrapper;
import com.mattqunell.receipts.database.ReceiptDbSchema.ReceiptTable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * ReceiptBook is implemented as a singleton, which is a class that allows only one instance of
 * itself to be created. It exists as long as the application is in memory, and is available through
 * lifecycle changes in activities and fragments. Singletons allow data to be easily passed between
 * controller classes, but should not be used for everything or as long-term storage solutions.
 */
class ReceiptBook {

    // The one ReceiptBook to use throughout the app
    private static ReceiptBook sReceiptBook;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    // Static getter that creates sReceiptBook if it doesn't exist and returns it
    static ReceiptBook get(Context context) {
        if (sReceiptBook == null) {
            sReceiptBook = new ReceiptBook(context);
        }

        return sReceiptBook;
    }

    // Private constructor to limit instantiation
    private ReceiptBook(Context context) {
        mContext = context;
        mDatabase = new ReceiptBaseHelper(mContext).getWritableDatabase();
    }

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

    // Helper method that essentially converts a Receipt into a ContentValues
    private static ContentValues getContentValues(Receipt receipt) {

        // ContentValues is a key-value class specifically designed for SQLite data
        ContentValues values = new ContentValues();

        values.put(ReceiptTable.Cols.UUID, receipt.getId().toString());
        values.put(ReceiptTable.Cols.LOCATION, receipt.getLocation());
        values.put(ReceiptTable.Cols.DATE, receipt.getDate().getTime());
        values.put(ReceiptTable.Cols.CARD, receipt.getCard());
        values.put(ReceiptTable.Cols.AMOUNT, receipt.getAmount().toString());
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
