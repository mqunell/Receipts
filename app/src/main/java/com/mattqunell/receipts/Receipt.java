package com.mattqunell.receipts;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public class Receipt implements Comparable<Receipt> {

    private UUID mId;
    private String mLocation;
    private Date mDate;
    private String mCard;
    private BigDecimal mAmount;
    private boolean mPaid;

    public Receipt() {
        this(UUID.randomUUID());
    }

    public Receipt(UUID id) {
        mId = id;
        mDate = new Date();
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

    String getCard() {
        return mCard;
    }

    public void setCard(String card) {
        mCard = card;
    }

    BigDecimal getAmount() {
        return mAmount;
    }

    public void setAmount(BigDecimal amount) {
        mAmount = amount;
    }

    boolean getPaid() {
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
