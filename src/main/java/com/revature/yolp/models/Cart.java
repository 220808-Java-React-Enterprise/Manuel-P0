package com.revature.yolp.models;

public class Cart {

    private String id;
    private int numItems;
    private User person;

    public Cart(){

    }
    public Cart(String id, int numItems, User person){
    this.id = id;
    this.numItems = numItems;
    this.person = person;
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

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

}
