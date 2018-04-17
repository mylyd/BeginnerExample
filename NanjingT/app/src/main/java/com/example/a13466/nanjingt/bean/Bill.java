package com.example.a13466.nanjingt.bean;

public class Bill {
    private int Bill_CarNumber;
    private int Bill_Money;
    private String Bill_Name;
    private String Bill_Time;

    public Bill(int bill_CarNumber, int bill_Money, String bill_Name, String bill_Time) {
        Bill_CarNumber = bill_CarNumber;
        Bill_Money = bill_Money;
        Bill_Name = bill_Name;
        Bill_Time = bill_Time;
    }

    public int getBill_CarNumber() {
        return Bill_CarNumber;
    }

    public void setBill_CarNumber(int bill_CarNumber) {
        Bill_CarNumber = bill_CarNumber;
    }

    public int getBill_Money() {
        return Bill_Money;
    }

    public void setBill_Money(int bill_Money) {
        Bill_Money = bill_Money;
    }

    public String getBill_Name() {
        return Bill_Name;
    }

    public void setBill_Name(String bill_Name) {
        Bill_Name = bill_Name;
    }

    public String getBill_Time() {
        return Bill_Time;
    }

    public void setBill_Time(String bill_Time) {
        Bill_Time = bill_Time;
    }
}
