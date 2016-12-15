package com.example.olya.shop.Controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.ItemCard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lily on 12/5/16.
 */

public class ItemCardCont {
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;


    public ItemCardCont(Context context)
    {
        dbHelper = new DbHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public long addItemCard(ItemCard itemCard) {
        ContentValues values = new ContentValues();

        values.put(dbHelper.KEY_ITEAMCARD_NAME, itemCard.name);
        values.put(dbHelper.KEY_ITEAMCARD_MEASURAREUNITS, itemCard.units);
        values.put(dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM, itemCard.nomenclNum);
        values.put(dbHelper.KEY_ITEAMCARD_ARTICULARNUM, itemCard.articNum);
        values.put(dbHelper.KEY_ITEAMCARD_BARCODE, itemCard.barCode);

        long _id = database.insert(dbHelper.TABLE_ITEMCARD, null, values);
        return _id;
    }

    public long findItemCard(String name) {
//        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE name = ? AND _id = ?", new String[] {name, "1"});
        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE name = ? ", new String[] {name });
        cursor.moveToFirst();
        long id1 = 0;
        while ( !cursor.isAfterLast()) {

            id1 = cursor.getLong(cursor.getColumnIndex("_id"));
            cursor.moveToNext();
        }
        return id1;
    }

    public List<ItemCard> getItemCards() {
        //створ список користувачів
        List<ItemCard> itemCardList = new ArrayList<ItemCard>();
        //створ строку запросу до бд для отримання всіх користувачів
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_ITEMCARD;
        //виконання запросу вище(selectQuery)
        Cursor cursor = database.rawQuery(selectQuery, null);
        //через курсор ходиш по витягнутим записам
        if (cursor.moveToFirst()) {
            do {
                //створ кор
                ItemCard itemCard = new ItemCard();
                //присвоюємо кор id
                itemCard.setId(cursor.getLong(0));
                //аналогічно
                itemCard.setName(cursor.getString(1));
                //аналогічно
                itemCard.setUnits(cursor.getDouble(2));
                itemCard.setNomenclNum(cursor.getString(3));
                itemCard.setArticNum(cursor.getString(4));
                itemCard.setBarCode(cursor.getLong(5));
                //додаєш кор в список
                itemCardList.add(itemCard);
            } while (cursor.moveToNext()); //рухаєшся до наступного кор
        }
        //отримано список
        return itemCardList;
    }

    public Cursor fetchAllCountries() {

        Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_ITEMCARD, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

}
