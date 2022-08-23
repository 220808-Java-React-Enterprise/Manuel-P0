package com.revature.LettuceInn.models;

public class Order {
    private String id;
    private int numItems;
    private double totalCost;
    private String date;
    private String person_id;
    private String location_id;

    public Order(){

    }

    public Order(String id, int numItems, double totalCost, String date, String person_id, String location_id){
        this.id = id;
        this.numItems = numItems;
        this.totalCost = totalCost;
        this.date = date;
        this.person_id = person_id;
        this.location_id = location_id;
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

    public String getPerson() {
        return person_id;
    }

    public void setPerson(String person) {
        this.person_id = person;
    }

    public String getLocation() {
        return location_id;
    }

    public void setLocation(String location) {
        this.location_id = location;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", numItems=" + numItems +
                ", totalCost=" + totalCost +
                ", date='" + date + '\'' +
                ", person=" + person_id +
                ", location=" + location_id +
                '}';
    }

}
