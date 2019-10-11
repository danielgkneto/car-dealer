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
    private int year;
    private double mSRP;
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;
    //TODO image

    public Car() {
    }

    public Car(String manufacturer, String model, int year, double mSRP, Category category) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.year = year;
        this.mSRP = mSRP;
        this.category = category;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
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
}