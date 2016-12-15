package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.PackInfo;
import com.example.olya.shop.Models.PackageType;

/**
 * Created by Olya on 11.12.2016.
 */

public class PackInfoCont {
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public PackInfoCont(Context context)
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

    public void addPackInfo(PackInfo packInfo) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_PACKINFO_PACKID, packInfo.pack_id);
        values.put(dbHelper.KEY_PACKINFO_ITEMCARDID, packInfo.itemcard_id);
        values.put(dbHelper.KEY_PACKINFO_AMOUNT, packInfo.amount);

        long _id = database.insert(dbHelper.TABLE_PACKINFO, null, values);
    }

    public Cursor fetchAllPacksInfo() {

        Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_PACKINFO, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}
