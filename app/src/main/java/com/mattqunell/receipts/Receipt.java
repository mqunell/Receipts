package com.mattqunell.receipts;

import java.math.BigDecimal;
import java.util.Date;

class Receipt implements Comparable<Receipt> {

    private String mLocation;
    private Date mDate;
    private String mCard;
    private BigDecimal mAmount;
    private boolean mWasPaidOut;

    Receipt() {
        mDate = new Date();
    }

    String getLocation() {
        return mLocation;
    }

    void setLocation(String location) {
        mLocation = location;
    }

    Date getDate() {
        return mDate;
    }

    void setDate(Date date) {
        mDate = date;
    }

    String getCard() {
        return mCard;
    }

    void setCard(String card) {
        mCard = card;
    }

    BigDecimal getAmount() {
        return mAmount;
    }

    void setAmount(BigDecimal amount) {
        mAmount = amount;
    }

    boolean getWasPaidOut() {
        return mWasPaidOut;
    }

    void setWasPaidOut(boolean out) {
        mWasPaidOut = out;
    }

    @Override
    public int compareTo(Receipt other) {
        return mDate.compareTo(other.getDate());
    }
}
