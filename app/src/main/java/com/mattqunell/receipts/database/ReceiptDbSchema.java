package com.mattqunell.receipts.database;

/*
 * ReceiptDbSchema contains an inner class that sets the names of the table and columns. Importing
 * ReceiptTable allows it to be accessed directly, without calling "ReceiptDbScheme." first.
 */
public class ReceiptDbSchema {

    // Inner class
    public static final class ReceiptTable {

        // Table name
        public static final String NAME = "receipts";

        // Column names
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String LOCATION = "location";
            public static final String DATE = "date";
            public static final String CARD = "card";
            public static final String AMOUNT = "amount";
            public static final String PAID = "paid";
        }
    }
}
