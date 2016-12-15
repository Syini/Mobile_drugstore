package com.example.olya.shop.Models;

/**
 * Created by lily on 12/7/16.
 */

public class PackageType {
    public long id;
    public String name;

    public PackageType() {}

    public PackageType(String name) {
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

}
