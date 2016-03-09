package com.mygod.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by siren93 on 16/1/6.
 */
/*顾客信息表*/
@Entity
@Table(name = "customers", uniqueConstraints = {@UniqueConstraint(columnNames = {"phone"})})
public class Customer {
    public enum Gender{
        男,女
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotNull
    @Column(length = 11)
    private String phone;

    @NotNull
    @Column(length = 16)
    private String password;

    @NotNull
    @Column(length = 16)
    private String name;

    @NotNull
    private Gender gender;

    @Temporal(TemporalType.DATE)
    private Date birthDay;

    public Customer(){}
    public Customer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

}
