package com.danielgkneto.mcjavabc.cardealer;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String manufacturer;
    private String model;
    private String year;
    private double mSRP;
    @ManyToOne()
    @JoinColumn(name="category_id")
    private Category category;
    @ManyToOne()
    @JoinColumn(name="user_id")
    private User user;
    //TODO image

    public Car() {
    }

    public Car(String manufacturer, String model, String year, double mSRP, Category category, User user) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.mSRP = mSRP;
        this.category = category;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getmSRP() {
        return mSRP;
    }

    public void setmSRP(double mSRP) {
        this.mSRP = mSRP;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}