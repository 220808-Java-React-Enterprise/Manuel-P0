package com.revature.yolp.models;

public class Order {
    private String id;
    private int numItems;
    private double totalCost;
    private String date;
    private User person;
    private Warehouse location;

    public Order(){

    }

    public Order(String id, int numItems, double totalCost, String date, User person, Warehouse location){
        this.id = id;
        this.numItems = numItems;
        this.totalCost = totalCost;
        this.date = date;
        this.person = person;
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public Warehouse getLocation() {
        return location;
    }

    public void setLocation(Warehouse location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", numItems=" + numItems +
                ", totalCost=" + totalCost +
                ", date='" + date + '\'' +
                ", person=" + person +
                ", location=" + location +
                '}';
    }

}
