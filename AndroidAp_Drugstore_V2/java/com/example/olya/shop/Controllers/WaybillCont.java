package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Waybill;
import com.example.olya.shop.Models.WriteOffAct;

/**
 * Created by Olya on 18.12.2016.
 */

public class WaybillCont {
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public WaybillCont(Context context) {
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

