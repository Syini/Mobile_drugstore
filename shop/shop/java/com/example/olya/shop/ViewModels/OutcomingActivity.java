package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.IncomingCont;
import com.example.olya.shop.Controllers.OutcomingCont;
import com.example.olya.shop.Models.Incoming;
import com.example.olya.shop.Models.Outcoming;
import com.example.olya.shop.R;

import java.util.Date;

/**
 * Created by Olya on 12.12.2016.
 */

public class OutcomingActivity extends Activity {
    OutcomingCont outcomingCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        outcomingCont = new OutcomingCont(getApplicationContext());
        //відкриваєм бд
        outcomingCont.open();

        Date date = new Date();
        //створ нового користув
        Outcoming outcoming = new Outcoming(1, date, 20);
        //додавання нов кор
        outcomingCont.addOutcoming(outcoming);
        outcomingCont.addOutcoming(new Outcoming(10, date, 440));

        outcomingCont.close();
    }
}