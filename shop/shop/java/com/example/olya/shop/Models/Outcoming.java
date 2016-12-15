package com.example.olya.shop.Models;

import java.util.Date;

/**
 * Created by Olya on 12.12.2016.
 */

public class Outcoming {
    public long id;
    public long buyer_id;
    public Date date;
    public long amount;

    public Outcoming() {}
    public Outcoming(long buyer_id, Date date, long amount)
    {
        this.buyer_id = buyer_id;
        this.date = date;
        this.amount = amount;
    }

    public void setId(long id) { this.id = id; }
    public void setVendorId(long buyer_id) { this.buyer_id = buyer_id; }
    public void setDate(Date date) { this.date = date; }
    public void setAmount(long amount) { this.amount = amount; }

}

