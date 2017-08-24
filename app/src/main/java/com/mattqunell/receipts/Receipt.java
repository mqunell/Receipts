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

    public String getCard() {
        return mCard;
    }

    public void setCard(String card) {
        mCard = card;
    }

    public BigDecimal getAmount() {
        return mAmount;
    }

    public void setAmount(BigDecimal amount) {
        mAmount = amount;
    }

    public boolean isOut() {
        return mWasPaidOut;
    }

    public void setOut(boolean out) {
        mWasPaidOut = out;
    }

    @Override
    public int compareTo(Receipt other) {
        return mDate.compareTo(other.getDate());
    }
}
