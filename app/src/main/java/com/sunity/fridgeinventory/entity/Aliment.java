package com.sunity.fridgeinventory.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Aliment {

    @PrimaryKey
    @ColumnInfo(name = "barCode")
    public double barCode;

    @ColumnInfo(name = "brand")
    private String brand;

    @ColumnInfo(name = "name")
    private String name;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBarCode() {
        return barCode;
    }

    public void setBarCode(double barCode) {
        this.barCode = barCode;
    }

}
