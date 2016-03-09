package com.mygod.model;

import org.apache.catalina.util.StringParser;

/**
 * Created by legolas on 2016/1/11.
 */
public class MerchandiseAndPackageReq {
    private int merchandise_id;
    private int package_id;
    private String name;
    private String type;
    private String merchandise_description;
    private String package_description;
    private int price;
    private int stock;

    public int getMerchandise_id() {
        return merchandise_id;
    }

    public void setMerchandise_id(int merchandise_id) {
        this.merchandise_id = merchandise_id;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMerchandise_description() {
        return merchandise_description;
    }

    public void setMerchandise_description(String merchandise_description) {
        this.merchandise_description = merchandise_description;
    }

    public String getPackage_description() {
        return package_description;
    }

    public void setPackage_description(String package_description) {
        this.package_description = package_description;
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
}
