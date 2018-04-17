package com.example.a13466.jl.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/3/22.
 */

public class BookVariable extends DataSupport {
    private int id;
    private int CO2;
    private int PM25;
    private int tem;
    private int hum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCO2() {
        return CO2;
    }

    public void setCO2(int CO2) {
        this.CO2 = CO2;
    }

    public int getPM25() {
        return PM25;
    }

    public void setPM25(int PM25) {
        this.PM25 = PM25;
    }

    public int getTem() {
        return tem;
    }

    public void setTem(int tem) {
        this.tem = tem;
    }

    public int getHum() {
        return hum;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }
}
