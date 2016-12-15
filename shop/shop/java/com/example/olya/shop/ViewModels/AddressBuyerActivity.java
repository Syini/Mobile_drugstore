package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.AddressBuyerCont;
import com.example.olya.shop.Controllers.AddressVendorCont;
import com.example.olya.shop.Models.AddressBuyer;
import com.example.olya.shop.Models.AddressVendor;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressBuyerActivity extends Activity {
    AddressBuyerCont addrBuyerCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        addrBuyerCont = new AddressBuyerCont(getApplicationContext());
        //відкриваєм бд
        addrBuyerCont.open();
        //створ нового користув
        AddressBuyer addressBuyer = new AddressBuyer(1, 1);
        //додавання нов кор
        addrBuyerCont.addAddressVendor(addressBuyer);
        addrBuyerCont.addAddressVendor(new AddressBuyer(2, 2));
        addrBuyerCont.addAddressVendor(new AddressBuyer(1, 10));

        addrBuyerCont.close();
    }
}

