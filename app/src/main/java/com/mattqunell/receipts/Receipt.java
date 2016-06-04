package com.mattqunell.receipts;

public class Receipt {
    String date;
    String place;
    double amount;
    int cardNum;

    public Receipt(String date, String place, double amount, int cardNum) {
        this.date = date;
        this.place = place;
        this.amount = amount;
        this.cardNum = cardNum;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public String toString() {
        String card = "";
        if (cardNum == 1) {
            card = "Debit";
        }
        else if (cardNum == 2) {
            card = "Credit 1";
        }
        else if (cardNum == 3) {
            card = "Credit 2";
        }

        return (date + "\t" + place + "\t" + String.valueOf(amount) + "\t" + card);
    }
}
