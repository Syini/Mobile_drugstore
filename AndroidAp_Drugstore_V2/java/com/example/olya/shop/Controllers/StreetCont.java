package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.City;
import com.example.olya.shop.Models.Street;

/**
 * Created by Olya on 12.12.2016.
 */

public class StreetCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public StreetCont(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addStreet(Street street) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_STREET_NAME, street.name);

        long _id = database.insert(dbHelper.TABLE_STREET, null, values);
    }
}