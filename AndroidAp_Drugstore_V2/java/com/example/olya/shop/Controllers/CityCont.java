package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.City;
import com.example.olya.shop.Models.Country;

/**
 * Created by Olya on 12.12.2016.
 */

public class CityCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public CityCont(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addCity(City city) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_CITY_NAME, city.name);

        long _id = database.insert(dbHelper.TABLE_CITY, null, values);
    }
}