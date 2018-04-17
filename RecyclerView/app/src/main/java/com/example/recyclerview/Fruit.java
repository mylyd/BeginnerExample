package com.example.recyclerview;

/**
 * Created by 13466 on 2018/3/7.
 */

public class Fruit {
    private String name;
    private int imageId;

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }

    public Fruit(String name, int imageId) {

        this.name = name;
        this.imageId = imageId;
    }
}
