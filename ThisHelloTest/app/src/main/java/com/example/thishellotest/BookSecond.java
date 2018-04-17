package com.example.thishellotest;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/2/4.
 * 这是用于设置账户阀值的表
 */

public class BookSecond extends DataSupport{
    private int idt;//这是序列号
    private String carnumber;//这是车号
    private String speedmax;//这是Max
    private String speedmin;//Min



    public int getIdt() {
        return idt;
    }

    public void setIdt(int idt) {
        this.idt = idt;
    }

    public String getCarnumber() {
        return carnumber;
    }

    public void setCarnumber(String carnumber) {
        this.carnumber = carnumber;
    }

    public String getSpeedmax() {
        return speedmax;
    }

    public void setSpeedmax(String speedmax) {
        this.speedmax = speedmax;
    }

    public String getSpeedmin() {
        return speedmin;
    }

    public void setSpeedmin(String speedmin) {
        this.speedmin = speedmin;
    }
}
