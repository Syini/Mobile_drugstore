package com.example.olya.shop.Models;

import java.util.Date;

/**
 * Created by Olya on 18.12.2016.
 */

public class Waybill {
    public long id;
    public long vendorid;
    public long addInfoWaybillId;
    public Date date;

    public Waybill() {}
    public Waybill(long vendorid, long addInfoWaybillId, Date date) {
        this.vendorid = vendorid;
        this.addInfoWaybillId = addInfoWaybillId;
        this.date = date;
    }


}
