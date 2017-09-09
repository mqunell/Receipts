package com.mattqunell.receipts.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.mattqunell.receipts.Receipt;
import com.mattqunell.receipts.database.ReceiptDbSchema.ReceiptTable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/*
 * ReceiptCursorWrapper is used to traverse the database through CursorWrapper's functionality. The
 * getReceipt() method parses and returns the Receipt at ReceiptCursorWrapper's location in the
 * database.
 */
public class ReceiptCursorWrapper extends CursorWrapper{

    public ReceiptCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    // Parses a Receipt from the database
    public Receipt getReceipt() {
        String uuid     = getString(getColumnIndex(ReceiptTable.Cols.UUID));
        String location = getString(getColumnIndex(ReceiptTable.Cols.LOCATION));
        long date       = getLong(getColumnIndex(ReceiptTable.Cols.DATE));
        String card     = getString(getColumnIndex(ReceiptTable.Cols.CARD));
        String amount   = getString(getColumnIndex(ReceiptTable.Cols.AMOUNT));
        int isPaid      = getInt(getColumnIndex(ReceiptTable.Cols.PAID));

        Receipt receipt = new Receipt(UUID.fromString(uuid));
        receipt.setLocation(location);
        receipt.setDate(new Date(date));
        receipt.setCard(card);
        receipt.setAmount(new BigDecimal(amount));
        receipt.setPaid(isPaid != 0);

        return receipt;
    }
}
