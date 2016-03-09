package com.mygod.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by siren93 on 16/1/7.
 */
/*套餐表*/
@Entity
@Table(name = "packages")
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    /*价格*/
    @NotNull
    private Integer price;
    /*描述*/
    @NotNull
    @Column(length = 100)
    private String description;
    /*库存*/
    @NotNull
    private Integer stock;
    /*外键货物id*/

    @NotNull
    @ManyToOne
    @JoinColumn(name = "merchandise_id")
    private Merchandise merchandise;

    public Package(){}

    public Package(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }
}
