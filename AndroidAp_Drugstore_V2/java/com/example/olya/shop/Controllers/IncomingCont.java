package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.Incoming;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by Olya on 12.12.2016.
 */

public class IncomingCont {
    //obj for accessing to tables
    private SQLiteDatabase database;
    //obj for accessing db
    private DbHelper dbHelper;

    //constructor
    public IncomingCont(Context context) {
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

    //add incoming item
    public void addIncoming(Incoming incoming) {
        ContentValues values = new ContentValues();

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String convertedDate = df.format(incoming.date);

        values.put(dbHelper.KEY_INCOMING_VENDORID, incoming.vendor_id);
        values.put(dbHelper.KEY_INCOMING_DATE, convertedDate);
        values.put(dbHelper.KEY_INCOMING_AMOUNT, incoming.amount);
        values.put(dbHelper.KEY_INCOMING_ITEMCARDID, incoming.itemcard_id);

        long _id = database.insert(dbHelper.TABLE_INCOMING, null, values);
    }

    //function for getting all incomings
    public Cursor fetchAllIncomings() {

        //Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_INCOMING, null);
        Cursor mCursor = database.rawQuery("SELECT incoming.vendor_id, incoming.itemcardid," +
                " incoming.date, incoming.amount, vendor._id, vendor.companyname_fop, itemcard._id, " +
                "itemcard.name, itemcard.nomenclNum FROM incoming, vendor, itemcard WHERE " +
                "incoming.vendor_id = vendor._id AND incoming.itemcardid = itemcard._id", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}