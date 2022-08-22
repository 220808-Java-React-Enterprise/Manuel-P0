package com.revature.yolp.models;

public class Cart {

    private String id;
    private int numItems;
    private String person_id;

    public Cart(){

    }
    public Cart(String id, int numItems, String person_id){
    this.id = id;
    this.numItems = numItems;
    this.person_id = person_id;
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

    public String getPerson() {
        return person_id;
    }

    public void setPerson(String person_id) {
        this.person_id = person_id;
    }

}
