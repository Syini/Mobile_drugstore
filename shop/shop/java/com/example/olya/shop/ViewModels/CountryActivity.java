package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.BuyerCont;
import com.example.olya.shop.Controllers.CountryCont;
import com.example.olya.shop.Models.Buyer;
import com.example.olya.shop.Models.Country;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class CountryActivity extends Activity {
    CountryCont countryCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        countryCont = new CountryCont(getApplicationContext());
        //відкриваєм бд
        countryCont.open();
        //створ нового користув
        Country country = new Country("Ukraine");
        //додавання нов кор
        countryCont.addCountry(country);
        countryCont.addCountry(new Country("Greece"));
        countryCont.addCountry(new Country("Netherlands"));

        countryCont.close();
    }
}
