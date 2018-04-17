package com.example.a13466.jlw.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/3/15.
 */

public class StopInfo extends DataSupport {
    private int id;
    private int stopName;
    private int bus;
    private int distance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStopName() {
        return stopName;
    }

    public void setStopName(int stopName) {
        this.stopName = stopName;
    }

    public int getBus() {
        return bus;
    }

    public void setBus(int bus) {
        this.bus = bus;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
