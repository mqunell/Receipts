package com.mattqunell.receipts;

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
