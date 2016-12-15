package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.ItemCard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by lily on 12/6/16.
 */

public class AdditICardInfoCont {
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;


    public AdditICardInfoCont(Context context)
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

    public void addAdditICardInfo(AdditICardInfo iCardInfo) {
        ContentValues values = new ContentValues();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String convertedDate = df.format(iCardInfo.date);

        values.put(dbHelper.KEY_ADDICARDINFO_PRICE, iCardInfo.price);
        values.put(dbHelper.KEY_ADDICARDINFO_ITEMCARDID, iCardInfo.itemCardID);
        values.put(dbHelper.KEY_ADDICARDINFO_DATE, convertedDate);

        long _id = database.insert(dbHelper.TABLE_ADDICARDINFO, null, values);
    }

}
