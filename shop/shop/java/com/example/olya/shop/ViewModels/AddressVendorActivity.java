package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.AddressVendorCont;
import com.example.olya.shop.Controllers.BuyerCont;
import com.example.olya.shop.Models.AddressVendor;
import com.example.olya.shop.Models.Buyer;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressVendorActivity extends Activity {
    AddressVendorCont addrVendorCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        addrVendorCont = new AddressVendorCont(getApplicationContext());
        //відкриваєм бд
        addrVendorCont.open();
        //створ нового користув
        AddressVendor addressVendor = new AddressVendor(1, 1);
        //додавання нов кор
        addrVendorCont.addAddressVendor(addressVendor);
        addrVendorCont.addAddressVendor(new AddressVendor(2, 10));
        addrVendorCont.addAddressVendor(new AddressVendor(10, 1));

        addrVendorCont.close();
    }
}
