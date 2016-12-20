package com.example.olya.shop.Models;

/**
 * Created by Olya on 18.12.2016.
 */

public class AdditWaybillInfo {
    public long id;
    public long itemId;
    public long waybillId;

    public AdditWaybillInfo() {}
    public AdditWaybillInfo(long itemId, long waybillId) {
        this.itemId = itemId;
        this.waybillId = waybillId;
    }

}
