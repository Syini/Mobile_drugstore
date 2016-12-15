package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.CityCont;
import com.example.olya.shop.Controllers.StreetCont;
import com.example.olya.shop.Models.City;
import com.example.olya.shop.Models.Street;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class StreetActivity extends Activity {
    StreetCont streetCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        streetCont = new StreetCont(getApplicationContext());
        //відкриваєм бд
        streetCont.open();
        //створ нового користув
        Street street = new Street("Maidan");
        //додавання нов кор
        streetCont.addStreet(street);
        streetCont.addStreet(new Street("Heroiv Nebesnoi Sotni"));
        streetCont.addStreet(new Street("Street 3"));

        streetCont.close();
    }
}
