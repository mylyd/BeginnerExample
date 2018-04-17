package com.example.a13466.jl.db;

import org.litepal.crud.DataSupport;

/**
 * Created by 13466 on 2018/3/21.
 */

public class BookEnvironmental extends DataSupport {
    private int id;
    private int distance_car_11;
    private int distance_car_12;
    private int distance_car_21;
    private int distance_car_22;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDistance_car_11() {
        return distance_car_11;
    }

    public void setDistance_car_11(int distance_car_11) {
        this.distance_car_11 = distance_car_11;
    }

    public int getDistance_car_12() {
        return distance_car_12;
    }

    public void setDistance_car_12(int distance_car_12) {
        this.distance_car_12 = distance_car_12;
    }

    public int getDistance_car_21() {
        return distance_car_21;
    }

    public void setDistance_car_21(int distance_car_21) {
        this.distance_car_21 = distance_car_21;
    }

    public int getDistance_car_22() {
        return distance_car_22;
    }

    public void setDistance_car_22(int distance_car_22) {
        this.distance_car_22 = distance_car_22;
    }
}
