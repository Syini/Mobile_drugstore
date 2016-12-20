package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Country;
import com.example.olya.shop.Models.Vendor;

/**
 * Created by Olya on 12.12.2016.
 */

public class CountryCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public CountryCont(Context context)
    {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addCountry(Country country) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_COUNTRY_NAME, country.name);

        long _id = database.insert(dbHelper.TABLE_COUNTRY, null, values);
    }
}
