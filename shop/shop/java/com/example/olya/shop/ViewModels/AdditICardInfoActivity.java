package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.example.olya.shop.Controllers.AdditICardInfoCont;
import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.ItemCard;
import com.example.olya.shop.R;

import java.util.Date;

/**
 * Created by lily on 12/6/16.
 */

public class AdditICardInfoActivity extends Activity {
    AdditICardInfoCont additICardInfoCont;
    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addit_icard_info);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);

        additICardInfoCont = new AdditICardInfoCont(getApplicationContext());
        //відкриваєм бд
        additICardInfoCont.open();

        Date date = new Date();
        AdditICardInfo addICardInfo = new AdditICardInfo(560.20, 1, date);
        //додавання нов кор
        additICardInfoCont.addAdditICardInfo(addICardInfo);
        additICardInfoCont.addAdditICardInfo(new AdditICardInfo(30000.5, 2, date));

        additICardInfoCont.close();

    }
}