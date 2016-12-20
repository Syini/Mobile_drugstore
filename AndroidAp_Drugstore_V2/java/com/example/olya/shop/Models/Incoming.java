package com.example.olya.shop.Models;

import java.util.Date;

/**
 * Created by Olya on 12.12.2016.
 */

public class Incoming {
    public long id;
    public long vendor_id;
    public Date date;
    public long amount;
    public long itemcard_id;

    public Incoming() {}
    public Incoming(long vendor_id, Date date, long amount, long itemcard_id)
    {
        this.vendor_id = vendor_id;
        this.date = date;
        this.amount = amount;
        this.itemcard_id = itemcard_id;
    }

    public void setId(long id) { this.id = id; }
    public void setVendorId(long vendor_id) { this.vendor_id = vendor_id; }
    public void setDate(Date date) { this.date = date; }
    public void setAmount(long amount) { this.amount = amount; }

}
