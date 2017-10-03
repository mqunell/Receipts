package com.mattqunell.receipts;

import android.content.Context;
import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

public class Receipt implements Comparable<Receipt> {

    private UUID mId;
    private String mLocation;
    private Date mDate;
    private int mCard;
    private String mAmount;
    private boolean mPaid;

    public Receipt() {
        this(UUID.randomUUID());
    }

    public Receipt(UUID id) {
        mId = id;
        mLocation = "";
        mDate = new Date();
        mCard = 0;
        mAmount = "";
        mPaid = true;
    }

    UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    int getCard() {
        return mCard;
    }

    public void setCard(int card) {
        mCard = card;
    }

    String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    boolean wasPaid() {
        return mPaid;
    }

    public void setPaid(boolean paid) {
        mPaid = paid;
    }

    public String toString(Context context) {
        String date = DateFormat.format("M/d", mDate).toString();
        String card = context.getResources().getStringArray(R.array.cards)[mCard];

        String output = date + "  "
                + mLocation + "  "
                + card + "  "
                + mAmount;

        if (!mPaid) {
            output += "**";
        }

        return output;
    }

    @Override
    public int compareTo(Receipt other) {
        return mDate.compareTo(other.getDate());
    }
}
