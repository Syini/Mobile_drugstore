package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Address;
import com.example.olya.shop.Models.City;

import static android.R.attr.country;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public AddressCont(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
        if (!database.isReadOnly()) {
            // Enable foreign key constraints
            database.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public void close() {
        dbHelper.close();
    }

    public void addAddress(Address address) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_ADDRESS_COUNTRYID, address.country_id);
        values.put(dbHelper.KEY_ADDRESS_CITYID, address.city_id);
        values.put(dbHelper.KEY_ADDRESS_STREETID, address.street_id);
        values.put(dbHelper.KEY_ADDRESS_HOUSENUM, address.house_num);

        long _id = database.insert(dbHelper.TABLE_ADDRESS, null, values);
    }
}