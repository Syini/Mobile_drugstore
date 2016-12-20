package com.example.olya.shop.Models;

/**
 * Created by Olya on 12.12.2016.
 */

public class Address {
    public long id;
    public long country_id;
    public long city_id;
    public long street_id;
    public String house_num;

    public Address() {}
    public Address(long country_id, long city_id, long street_id, String house_num) {
        this.country_id = country_id;
        this.city_id = city_id;
        this.street_id = street_id;
        this.house_num = house_num;
    }

    public void setId(long id) { this.id = id; }
    public void setCountryId(long country_id) { this.country_id = country_id; }
    public void setCityId(long city_id) { this.city_id = city_id; }
    public void setStreetId(long street_id) { this.street_id = street_id; }
    public void setHouseNum(String house_num) { this.house_num = house_num; }
}
