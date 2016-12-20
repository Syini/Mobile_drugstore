package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.ItemCard;
import com.example.olya.shop.Models.WriteOffAct;

/**
 * Created by Olya on 18.12.2016.
 */

public class WriteOffActCont {
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public WriteOffActCont(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addWriteOffAct(WriteOffAct writeOffAct) {
        ContentValues values = new ContentValues();

        //values.put(dbHelper.KEY_ITEAMCARD_NAME, itemCard.name);

        long _id = database.insert(dbHelper.TABLE_ITEMCARD, null, values);
        return _id;
    }
}

