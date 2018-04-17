package com.example.a13466.jlw.db;

import org.litepal.crud.DataSupport;

/**
 * Created by tuxzx on 2018/3/15.
 */

public class Envir extends DataSupport {
    private int id;
    private int temperature;
    private int humidity;
    private int LightIntensity;
    private int co2;
    private int pm25;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getLightIntensity() {
        return LightIntensity;
    }

    public void setLightIntensity(int lightIntensity) {
        LightIntensity = lightIntensity;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }
}
