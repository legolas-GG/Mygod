package com.mygod.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Not;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by siren93 on 16/1/7.
 */
/*用户地址表*/
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /*如果实体customer中与Address存在OneToMany约束关系，
    在Address实体类的customer加上@JsonIgnore注解，
    在返回时忽略解析该字段，避免循环引用造成的栈溢出*/
    /*@JsonIgnore*/
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @NotNull
    private Customer customer;

    @Column(length = 16)
    @NotNull
    private String province;

    @Column(length = 16)
    @NotNull
    private String city;

    @Column(length = 16)
    private String district;

    private String street;

    public Address(){}
    public Address(Integer id){
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
