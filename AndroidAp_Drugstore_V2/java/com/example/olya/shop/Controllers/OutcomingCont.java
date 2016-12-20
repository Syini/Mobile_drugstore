package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Incoming;
import com.example.olya.shop.Models.Outcoming;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Olya on 12.12.2016.
 */

public class OutcomingCont {
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;

    public OutcomingCont(Context context) {
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

    public void addOutcoming(Outcoming outcoming) {
        ContentValues values = new ContentValues();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String convertedDate = df.format(outcoming.date);

        values.put(dbHelper.KEY_OUTCOMING_BUYERID, outcoming.buyer_id);
        values.put(dbHelper.KEY_OUTCOMING_DATE, convertedDate);
        values.put(dbHelper.KEY_OUTCOMING_AMOUNT, outcoming.amount);
        values.put(dbHelper.KEY_OUTCOMING_AMOUNT, outcoming.itemcardid);

        long _id = database.insert(dbHelper.TABLE_OUTCOMING, null, values);
    }

    public Cursor fetchAllOutcomings() {

        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_OUTCOMING;
        Cursor mCursor = database.rawQuery(selectQuery, null);
        /*Cursor mCursor = database.rawQuery("SELECT outcoming.buyer_id, outcoming.itemcardid," +
                " outcoming.date, outcoming.amount, buyer._id, buyer.companyname_fop, itemcard._id, " +
                "itemcard.name, itemcard.nomenclNum FROM outcoming, buyer, itemcard WHERE " +
                "outcoming.buyer_id = buyer._id AND outcoming.itemcardid = itemcard._id", null);
*/
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
