package com.mattqunell.receipts.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mattqunell.receipts.database.ReceiptDbSchema.ReceiptTable;

/*
 * When an instance of ReceiptBaseHelper is created, the super(...) call in the constructor does up
 * to three things, as necessary:
 *     1. Create the database and set the version number (onCreate(...))
 *     2. Upgrade the database (onUpgrade(...))
 *     3. Open the database
 */
public class ReceiptBaseHelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "receiptBase.db";

    public ReceiptBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // Called automatically when a database needs to be created
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create the Receipt table
        db.execSQL("create table " + ReceiptTable.NAME + "(" +
                " _id integer primary key autoincrement, " +
                ReceiptTable.Cols.UUID     + ", " +
                ReceiptTable.Cols.LOCATION + ", " +
                ReceiptTable.Cols.DATE     + ", " +
                ReceiptTable.Cols.CARD     + ", " +
                ReceiptTable.Cols.AMOUNT   + ", " +
                ReceiptTable.Cols.PAID     +
                ")"
        );
    }

    // Called automatically when a database needs to be upgraded
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Intentionally left blank
    }
}
