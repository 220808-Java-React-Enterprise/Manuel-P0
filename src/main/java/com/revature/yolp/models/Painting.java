package com.revature.yolp.models;

public class Painting {

    private String id;
    private String name;
    private String author;
    private String image;
    private boolean isAvailable;
    private double cost;

    public Painting() {

    }

    public Painting(String id, String name, String author, String image, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.image = image;
        this.isAvailable = isAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public double getCost(){
        return cost;
    }

    public void setCost(double cost){
        this.cost = cost;
    }

}
