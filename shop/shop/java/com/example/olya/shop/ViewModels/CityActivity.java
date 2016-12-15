package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.CityCont;
import com.example.olya.shop.Controllers.CountryCont;
import com.example.olya.shop.Models.City;
import com.example.olya.shop.Models.Country;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class CityActivity extends Activity {
    CityCont cityCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        cityCont = new CityCont(getApplicationContext());
        //відкриваєм бд
        cityCont.open();
        //створ нового користув
        City city = new City("Kyiv");
        //додавання нов кор
        cityCont.addCity(city);
        cityCont.addCity(new City("Rome"));
        cityCont.addCity(new City("Amsterdam"));

        cityCont.close();
    }
}
