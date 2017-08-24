package com.mattqunell.receipts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

class ReceiptBook {

    // The one ReceiptBook to use throughout the app
    private static ReceiptBook sReceiptBook;

    // The List of Receipts
    private List<Receipt> mReceipts;

    // Public getter method for the ReceiptBook
    static ReceiptBook get() {
        if (sReceiptBook == null) {
            sReceiptBook = new ReceiptBook();
        }

        return sReceiptBook;
    }

    // Private constructor to limit instantiation
    private ReceiptBook() {
        mReceipts = new ArrayList<>();

        // Dummy Receipts for testing
        for (int i = 0; i < 25; i++) {
            Receipt temp = new Receipt();

            temp.setLocation("Fry's");
            // Date is set automatically
            temp.setCard("Chase");
            temp.setAmount(new BigDecimal("49.99"));
            temp.setWasPaidOut(true);

            mReceipts.add(temp);
        }
    }

    void addReceipt(Receipt r) {
        mReceipts.add(r);
    }

    void removeReceipt(Receipt r) {
        mReceipts.remove(r);
    }

    List<Receipt> getReceipts() {
        return mReceipts;
    }
}
