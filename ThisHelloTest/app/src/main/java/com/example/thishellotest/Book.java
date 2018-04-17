package com.example.thishellotest;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/2/1.
 * 这是用于查询历史账单的表
 */

public class Book extends DataSupport {
    private int ide;//序号
    private String number;//车号
    private String money;//充值金额
    private String namee;//操作人
    private String time;//操作时间

    public int getIde() {
        return ide;
    }

    public void setIde(int ide) {
        this.ide = ide;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getNamee() {
        return namee;
    }

    public void setNamee(String namee) {
        this.namee = namee;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
