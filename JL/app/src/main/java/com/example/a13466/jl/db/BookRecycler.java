package com.example.a13466.jl.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/3/20.
 */

public class BookRecycler extends DataSupport {
    private int id;
    private int carnum;
    private int redtime;
    private int greentime;
    private int yellowtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCarnum() {
        return carnum;
    }

    public void setCarnum(int carnum) {
        this.carnum = carnum;
    }

    public int getRedtime() {
        return redtime;
    }

    public void setRedtime(int redtime) {
        this.redtime = redtime;
    }

    public int getGreentime() {
        return greentime;
    }

    public void setGreentime(int greentime) {
        this.greentime = greentime;
    }

    public int getYellowtime() {
        return yellowtime;
    }

    public void setYellowtime(int yellowtime) {
        this.yellowtime = yellowtime;
    }
}
