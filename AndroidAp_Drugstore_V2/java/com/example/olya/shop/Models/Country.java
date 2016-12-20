package com.example.olya.shop.Models;

/**
 * Created by Olya on 12.12.2016.
 */

public class Country {
    public long id;
    public String name;

    public Country() {}
    public Country(String name) { this.name = name; }

    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
}
