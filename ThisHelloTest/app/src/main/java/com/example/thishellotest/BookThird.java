package com.example.thishellotest;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/2/5.
 * 这是用于设置小车速度的表
 */

public class BookThird extends DataSupport{
    private int f1idt;//这是序列号
    private String f1carnumber;//这是车号
    private String f1speedmax;//这是Max
    private String f1speedmin;//Min

    public int getF1idt() {
        return f1idt;
    }

    public void setF1idt(int f1idt) {
        this.f1idt = f1idt;
    }

    public String getF1carnumber() {
        return f1carnumber;
    }

    public void setF1carnumber(String f1carnumber) {
        this.f1carnumber = f1carnumber;
    }

    public String getF1speedmax() {
        return f1speedmax;
    }

    public void setF1speedmax(String f1speedmax) {
        this.f1speedmax = f1speedmax;
    }

    public String getF1speedmin() {
        return f1speedmin;
    }

    public void setF1speedmin(String f1speedmin) {
        this.f1speedmin = f1speedmin;
    }
}
