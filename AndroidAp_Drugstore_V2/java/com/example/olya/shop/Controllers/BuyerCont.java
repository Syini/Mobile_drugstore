package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Buyer;
import com.example.olya.shop.Models.Vendor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olya on 12.12.2016.
 */

public class BuyerCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public BuyerCont(Context context)
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

    public void addBuyer(Buyer buyer) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_BUYER_COMPANYNAMEFOP, buyer.companyName_FOP);
        values.put(dbHelper.KEY_BUYER_EDRPOU_DRFO, buyer.EDRPOU_DRFO);
        values.put(dbHelper.KEY_BUYER_SIGN, buyer.sign);

        long _id = database.insert(dbHelper.TABLE_BUYER, null, values);
    }

    public List<String> getBuyersNames() {
        //створ список користувачів
        List<String> vendorsNamesList = new ArrayList<String>();
        //створ строку запросу до бд для отримання всіх користувачів
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_BUYER;
        //виконання запросу вище(selectQuery)
        Cursor cursor = database.rawQuery(selectQuery, null);
        //через курсор ходиш по витягнутим записам
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                vendorsNamesList.add(name);
            } while (cursor.moveToNext()); //рухаєшся до наступного кор
        }
        //отримано список
        return vendorsNamesList;
    }
}
