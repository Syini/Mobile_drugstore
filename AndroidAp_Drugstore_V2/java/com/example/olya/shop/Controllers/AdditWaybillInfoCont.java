package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditWaybillInfo;
import com.example.olya.shop.Models.Waybill;

/**
 * Created by Olya on 18.12.2016.
 */

//information about buyers and vendors waybills
public class AdditWaybillInfoCont {
    //object for accessing tables
    private SQLiteDatabase database;
    //object for accessing db
    private DbHelper dbHelper;

    public AdditWaybillInfoCont(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addWaybill(Waybill waybill) {
        ContentValues values = new ContentValues();

        //values.put(dbHelper.KEY_ITEAMCARD_NAME, itemCard.name);
        long _id = database.insert(dbHelper.TABLE_ITEMCARD, null, values);
        return _id;
    }
}

