package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.PackInfo;
import com.example.olya.shop.Models.Vendor;

import java.util.ArrayList;
import java.util.List;

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

    public long addVendor(Vendor vendor) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_VENDOR_COMPANYNAMEFOP, vendor.companyName_FOP);
        values.put(dbHelper.KEY_VENDOR_EDRPOU_DRFO, vendor.EDRPOU_DRFO);
        values.put(dbHelper.KEY_VENDOR_SIGN, vendor.sign);

        long id = database.insert(dbHelper.TABLE_VENDOR, null, values);
        return id;
    }

    public long findVendor(String name) {
//        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE name = ? AND _id = ?", new String[] {name, "1"});
        Cursor cursor = database.rawQuery("SELECT * FROM vendor WHERE companyname_fop = ? ", new String[] {name });
        cursor.moveToFirst();
        long id1 = 0;
        while ( !cursor.isAfterLast()) {

            id1 = cursor.getLong(cursor.getColumnIndex("_id"));
            cursor.moveToNext();
        }
        return id1;
    }

    public List<String> getVendorsNames() {
        //створ список користувачів
        List<String> vendorsNamesList = new ArrayList<String>();
        //створ строку запросу до бд для отримання всіх користувачів
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_VENDOR;
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
