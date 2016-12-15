package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.AddressCont;
import com.example.olya.shop.Controllers.CountryCont;
import com.example.olya.shop.Models.Address;
import com.example.olya.shop.Models.Country;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressActivity extends Activity {
    AddressCont addressCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        addressCont = new AddressCont(getApplicationContext());
        //відкриваєм бд
        addressCont.open();
        //створ нового користув
        Address address = new Address(1, 1, 1, "2/2");
        //додавання нов кор
        addressCont.addAddress(address);
        addressCont.addAddress(new Address(3, 3, 3, "3"));

        addressCont.close();
    }
}
