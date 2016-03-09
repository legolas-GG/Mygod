package com.mygod.model;

/**
 * Created by legolas on 2016/1/11.
 */
public class TypeReq {
    private Integer merchandise_id;
    private String name;
    private String description;
    private int price;
    private int stock;
    private boolean iscollected = false;

    public Integer getMerchandise_id() {
        return merchandise_id;
    }

    public void setMerchandise_id(Integer merchandise_id) {
        this.merchandise_id = merchandise_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean iscollected() {
        return iscollected;
    }

    public void setIscollected(boolean iscollected) {
        this.iscollected = iscollected;
    }
}
