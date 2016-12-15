package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Address;
import com.example.olya.shop.Models.AddressVendor;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressVendorCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public AddressVendorCont(Context context) {
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

    public void addAddressVendor(AddressVendor addressVendor) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_ADDRESSVENDOR_VENDORID, addressVendor.vendor_id);
        values.put(dbHelper.KEY_ADDRESSVENDOR_ADDRESSID, addressVendor.address_id);

        long _id = database.insert(dbHelper.TABLE_ADDRESSVENDOR, null, values);
    }
}
