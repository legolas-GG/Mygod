package com.mygod.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by siren93 on 16/1/7.
 */
/*货物表*/
@Entity
@Table(name = "merchandise")
public class Merchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 16)
    @NotNull
    private String name;

    @Column(length = 16)
    private String typename;

    private String description;
    //TODO 使用注解方法解决 级联查询 问题
    /*@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class ,property="@id")*/
    @OneToMany(mappedBy = "merchandise")
    @Cascade(value = CascadeType.ALL)
    private List<Package> packages = new ArrayList<Package>();

    public  Merchandise(){};

    public Merchandise(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
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

    public List<Package> getPackages() {
        return packages;
    }

    public void setPackages(List<Package> packages) {
        this.packages = packages;
    }
}
