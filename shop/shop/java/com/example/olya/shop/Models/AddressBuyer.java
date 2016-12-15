package com.example.olya.shop.Models;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressBuyer {
    public long id;
    public long address_id;
    public long buyer_id;

    public AddressBuyer() {}
    public AddressBuyer(long address_id, long buyer_id)
    {
        this.address_id = address_id;
        this.buyer_id = buyer_id;
    }

    public void setAddressId(long address_id) { this.address_id = address_id; }
    public void setVendorId(long vendor_id) { this.buyer_id = buyer_id; }

}

