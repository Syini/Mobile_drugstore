package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.PackInfo;
import com.example.olya.shop.Models.Vendor;

/**
 * Created by Olya on 12.12.2016.
 */

public class VendorCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public VendorCont(Context context)
    {
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

    public void addVendor(Vendor vendor) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_VENDOR_COMPANYNAMEFOP, vendor.companyName_FOP);
        values.put(dbHelper.KEY_VENDOR_EDRPOU_DRFO, vendor.EDRPOU_DRFO);
        values.put(dbHelper.KEY_VENDOR_SIGN, vendor.sign);

        long _id = database.insert(dbHelper.TABLE_VENDOR, null, values);
    }
}
