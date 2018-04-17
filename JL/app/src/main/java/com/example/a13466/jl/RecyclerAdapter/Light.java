package com.example.a13466.jl.RecyclerAdapter;

/**
 * Created by 13466 on 2018/3/20.
 */

public class Light {
    private String Car;
    private int redlight;
    private int greenlight;
    private int yellowlight;

    public Light(String car, int redlight, int greenlight, int yellowlight) {
        Car = car;
        this.redlight = redlight;
        this.greenlight = greenlight;
        this.yellowlight = yellowlight;
    }

    public String getCar() {
        return Car;
    }

    public void setCar(String car) {
        Car = car;
    }

    public int getRedlight() {
        return redlight;
    }

    public void setRedlight(int redlight) {
        this.redlight = redlight;
    }

    public int getGreenlight() {
        return greenlight;
    }

    public void setGreenlight(int greenlight) {
        this.greenlight = greenlight;
    }

    public int getYellowlight() {
        return yellowlight;
    }

    public void setYellowlight(int yellowlight) {
        this.yellowlight = yellowlight;
    }
}
