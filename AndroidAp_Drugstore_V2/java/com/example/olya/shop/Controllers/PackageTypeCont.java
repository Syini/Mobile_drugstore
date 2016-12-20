package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public long addPackType2(PackageType packageType) {
        ContentValues values = new ContentValues();
        values.put(dbHelper.KEY_PACKTYPE_NAME, packageType.name);

        long _id = database.insert(dbHelper.TABLE_PACKTYPE, null, values);
        return _id;
    }

    public long findPackageType(String name) {
//        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE name = ? AND _id = ?", new String[] {name, "1"});
        Cursor cursor = database.rawQuery("SELECT * FROM packagetype WHERE packName = ? ", new String[] {name });
        cursor.moveToFirst();
        long id1 = 0;
        while (!cursor.isAfterLast()) {

            id1 = cursor.getLong(cursor.getColumnIndex("pack_id"));
            cursor.moveToNext();
        }
        return id1;
    }

    public Cursor fetchAllPackages() {

        Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_PACKTYPE, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
}

