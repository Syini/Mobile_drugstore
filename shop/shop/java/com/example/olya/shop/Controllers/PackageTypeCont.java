package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.PackageType;

/**
 * Created by lily on 12/7/16.
 */

public class PackageTypeCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public PackageTypeCont(Context context)
    {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void addPackType(PackageType packageType) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_PACKTYPE_NAME, packageType.name);

        long _id = database.insert(dbHelper.TABLE_PACKTYPE, null, values);
    }

}
