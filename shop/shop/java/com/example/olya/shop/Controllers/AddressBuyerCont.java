package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AddressBuyer;
import com.example.olya.shop.Models.AddressVendor;

/**
 * Created by Olya on 12.12.2016.
 */

public class AddressBuyerCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public AddressBuyerCont(Context context) {
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

    public void addAddressVendor(AddressBuyer addressBuyer) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_ADDRESSBUYER_BUYERID, addressBuyer.buyer_id);
        values.put(dbHelper.KEY_ADDRESSBUYER_ADDRESSID, addressBuyer.address_id);

        long _id = database.insert(dbHelper.TABLE_ADDRESSBUYER, null, values);
    }
}
