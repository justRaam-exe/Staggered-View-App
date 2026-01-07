package com.raam.weebapp;

public class ImageModel {
    public int id;
    public String imageName;
    public String title;
    public String description;

    public ImageModel (int id, String imageName, String title, String description) {
        this.id = id;
        this.imageName = imageName;
        this.title = title;
        this.description = description;
    }
}
