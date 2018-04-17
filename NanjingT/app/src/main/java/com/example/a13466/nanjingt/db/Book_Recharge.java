package com.example.a13466.nanjingt.db;

import org.litepal.crud.DataSupport;

public class Book_Recharge extends DataSupport {
    private int id;
    private int Recharge_CarNumber;
    private int Recharge_Money;
    private String Recharge_Name;
    private String Recharge_Time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecharge_CarNumber() {
        return Recharge_CarNumber;
    }

    public void setRecharge_CarNumber(int recharge_CarNumber) {
        Recharge_CarNumber = recharge_CarNumber;
    }

    public int getRecharge_Money() {
        return Recharge_Money;
    }

    public void setRecharge_Money(int recharge_Money) {
        Recharge_Money = recharge_Money;
    }

    public String getRecharge_Name() {
        return Recharge_Name;
    }

    public void setRecharge_Name(String recharge_Name) {
        Recharge_Name = recharge_Name;
    }

    public String getRecharge_Time() {
        return Recharge_Time;
    }

    public void setRecharge_Time(String recharge_Time) {
        Recharge_Time = recharge_Time;
    }
}
