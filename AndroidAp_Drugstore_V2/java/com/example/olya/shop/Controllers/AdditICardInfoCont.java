package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.ItemCard;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by lily on 12/6/16.
 */


//class with additional information about item
public class AdditICardInfoCont {
    //object for accessing to tables
    private SQLiteDatabase database;
    //object for accessing to database
    private DbHelper dbHelper;

    //initialize a constructor
    public AdditICardInfoCont(Context context)
    {
        dbHelper = new DbHelper(context);
    }

    //open database
    public void open() {
        database = dbHelper.getWritableDatabase();
        //add possibility to use FOREIGN KEY when create database, tables....
        if (!database.isReadOnly()) {
            // Enable foreign key constraints
            database.execSQL("PRAGMA foreign_keys=ON;");

        }
    }

    //close connection to database
    public void close() {
        dbHelper.close();
    }
    //function for adding item info
    public void addAdditICardInfo(AdditICardInfo iCardInfo) {
        ContentValues values = new ContentValues();
        //take today date and adding to table
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String convertedDate = df.format(iCardInfo.date);

        values.put(dbHelper.KEY_ADDICARDINFO_PRICE, iCardInfo.price);
        values.put(dbHelper.KEY_ADDICARDINFO_ITEMCARDID, iCardInfo.itemCardID);
        values.put(dbHelper.KEY_ADDICARDINFO_DATE, convertedDate);

        long _id = database.insert(dbHelper.TABLE_ADDICARDINFO, null, values);
    }
    //get all items info using Cursor
    public Cursor fetchAllAddItemInfo() {
        String[] columns = new String[]{
                dbHelper.KEY_ADDICARDINFO_ID,
                dbHelper.KEY_ADDICARDINFO_PRICE,
                dbHelper.KEY_ADDICARDINFO_ITEMCARDID,
                dbHelper.KEY_ADDICARDINFO_DATE
        };
        //Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_ITEMCARD, null);
        //Cursor c = database.query(dbHelper.TABLE_ADDICARDINFO, columns, null, null, null, null, dbHelper.KEY_ITEAMCARD_NAME+" DESC");
        //Cursor c = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_ADDICARDINFO, null);
        Cursor c = database.rawQuery("SELECT itemcard._id, addit_icard_info._id," +
                " itemcard.name, addit_icard_info._price, addit_icard_info.itemCardID, addit_icard_info.date " +
                " FROM itemcard, addit_icard_info WHERE " +
                "itemcard._id = addit_icard_info._id", null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}
