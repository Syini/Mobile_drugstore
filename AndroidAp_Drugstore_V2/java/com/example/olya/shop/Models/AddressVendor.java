package com.example.olya.shop.Models;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressVendor {
    public long id;
    public long address_id;
    public long vendor_id;

    public AddressVendor() {}
    public AddressVendor(long address_id, long vendor_id)
    {
        this.address_id = address_id;
        this.vendor_id = vendor_id;
    }

    public void setAddressId(long address_id) { this.address_id = address_id; }
    public void setVendorId(long vendor_id) { this.vendor_id = vendor_id; }

}
