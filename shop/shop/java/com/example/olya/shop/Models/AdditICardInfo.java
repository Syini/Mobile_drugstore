package com.example.olya.shop.Models;

import java.util.Date;

/**
 * Created by lily on 12/6/16.
 */

public class AdditICardInfo {
    public long id;
    public double price;
    public long itemCardID;
    public Date date;

    public AdditICardInfo()
    {
    }
    public AdditICardInfo(double price, long itemCardID, Date date)
    {
        this.price = price;
        this.itemCardID = itemCardID;
        this.date = date;

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnits(long itemCardID) {
        this.itemCardID = itemCardID;
    }

    public void setNomenclNum(Date date) {
        this.date = date;
    }


}