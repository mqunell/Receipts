package com.mattqunell.receipts.data;

import android.content.Context;
import android.text.format.DateFormat;

import com.mattqunell.receipts.R;

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

    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        mId = id;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public int getCard() {
        return mCard;
    }

    public void setCard(int card) {
        mCard = card;
    }

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public boolean wasPaid() {
        return mPaid;
    }

    public void setPaid(boolean paid) {
        mPaid = paid;
    }

    @Override
    public int compareTo(Receipt other) {
        return mDate.compareTo(other.getDate());
    }
}
