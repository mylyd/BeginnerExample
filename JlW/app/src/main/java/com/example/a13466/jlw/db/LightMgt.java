package com.example.a13466.jlw.db;

import org.litepal.crud.DataSupport;

/**
 * Created by tuxzx on 2018/3/13.
 */

public class LightMgt extends DataSupport {

    private long id;
    private String roadName;
    private int redTime;
    private int greenTime;
    private int yellowTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(String roadName) {
        this.roadName = roadName;
    }

    public int getRedTime() {
        return redTime;
    }

    public void setRedTime(int redTime) {
        this.redTime = redTime;
    }

    public int getGreenTime() {
        return greenTime;
    }

    public void setGreenTime(int greenTime) {
        this.greenTime = greenTime;
    }

    public int getYellowTime() {
        return yellowTime;
    }

    public void setYellowTime(int yellowTime) {
        this.yellowTime = yellowTime;
    }
}
