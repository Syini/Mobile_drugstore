package com.example.olya.shop.Models;

/**
 * Created by Olya on 11.12.2016.
 */

public class PackInfo {
    public long id;
    public long pack_id;
    public long itemcard_id;
    public long amount;

    public PackInfo() {}

    public PackInfo(long pack_id, long itemcard_id, long amount)
    {
        this.pack_id = pack_id;
        this.itemcard_id = itemcard_id;
        this.amount = amount;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setPackId(long pack_id) {
        this.pack_id = pack_id;
    }
    public void setItemCardId(long itemcard_id) {
        this.itemcard_id = itemcard_id;
    }
    public void setAmount(long amount) {
        this.amount = amount;
    }

}
