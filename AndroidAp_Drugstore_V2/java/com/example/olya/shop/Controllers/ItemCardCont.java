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

    public void updateItemCard(long id, String name, long units, String nomenclNum, String articNum, long barCode) {
        ContentValues values = new ContentValues();

        values.put(dbHelper.KEY_ITEAMCARD_NAME, name);
        values.put(dbHelper.KEY_ITEAMCARD_MEASURAREUNITS, units);
        values.put(dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM, nomenclNum);
        values.put(dbHelper.KEY_ITEAMCARD_ARTICULARNUM, articNum);
        values.put(dbHelper.KEY_ITEAMCARD_BARCODE, barCode);

        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(id)};

        database.update(dbHelper.TABLE_ITEMCARD, values, where, whereArgs);
        //return _id;
    }

    public long deleteItemCard(long id) {
        long _id = database.delete(dbHelper.TABLE_ITEMCARD, dbHelper.KEY_ITEAMCARD_ID + "=?", new String[]{String.valueOf(id)});
        return _id;
    }

    public List<String> getItemValuesInStringList() {
        List<String> itemsList = new ArrayList<String>();
        String selectQuery = "SELECT name, measureUnits FROM itemcard";
        Cursor cursor = database.rawQuery(selectQuery, null);
        //через курсор ходиш по витягнутим записам
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String units = cursor.getString(1);
                String res = name + " " + units;

                itemsList.add(res);
            } while (cursor.moveToNext()); //рухаєшся до наступного кор
        }
        //отримано список
        return itemsList;
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

    public long findItemCardNomNum(String nomnum) {
//        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE name = ? AND _id = ?", new String[] {name, "1"});
        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE nomenclNum = ? ", new String[] { nomnum });
        cursor.moveToFirst();
        long isExistedNomnum = 0;
        while ( !cursor.isAfterLast()) {

            isExistedNomnum = cursor.getLong(cursor.getColumnIndex("_id"));
            cursor.moveToNext();
        }
        return isExistedNomnum;
    }

    public List<String> getNomNumsByName(String name) {
        List<String> nomnumsList = new ArrayList<String>();
        Cursor cursor = database.rawQuery("SELECT * FROM itemcard WHERE name = ? ", new String[] { name });
        //через курсор ходиш по витягнутим записам
        if (cursor.moveToFirst()) {
            do {
                //створ кор
                String nomnum = cursor.getString(3);
                nomnumsList.add(nomnum);
            } while (cursor.moveToNext()); //рухаєшся до наступного кор
        }
        //отримано список
        return nomnumsList;
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

    public List<String> getItemsNames() {
        //створ список користувачів
        List<String> itemsNamesList = new ArrayList<String>();
        //створ строку запросу до бд для отримання всіх користувачів
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_ITEMCARD;
        //виконання запросу вище(selectQuery)
        Cursor cursor = database.rawQuery(selectQuery, null);
        //через курсор ходиш по витягнутим записам
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(1);
                itemsNamesList.add(name);
            } while (cursor.moveToNext()); //рухаєшся до наступного кор
        }
        //отримано список
        return itemsNamesList;
    }

    public List<String> getItemsNomNums() {
        //створ список користувачів
        List<String> itemsNomNumsList = new ArrayList<String>();
        //створ строку запросу до бд для отримання всіх користувачів
        String selectQuery = "SELECT * FROM " + dbHelper.TABLE_ITEMCARD;
        //виконання запросу вище(selectQuery)
        Cursor cursor = database.rawQuery(selectQuery, null);
        //через курсор ходиш по витягнутим записам
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(3);
                itemsNomNumsList.add(name);
            } while (cursor.moveToNext()); //рухаєшся до наступного кор
        }
        //отримано список
        return itemsNomNumsList;
    }


    public Cursor fetchAllItems() {
        String[] columns = new String[]{
                dbHelper.KEY_ITEAMCARD_ID,
                dbHelper.KEY_ITEAMCARD_NAME,
                dbHelper.KEY_ITEAMCARD_MEASURAREUNITS,
                dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM,
                dbHelper.KEY_ITEAMCARD_ARTICULARNUM,
                dbHelper.KEY_ITEAMCARD_BARCODE

        };
        //Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_ITEMCARD, null);
        Cursor c = database.query(dbHelper.TABLE_ITEMCARD, columns, null, null, null, null, dbHelper.KEY_ITEAMCARD_NAME+" DESC");
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public Cursor getItemCard(long itemid) {
        String[] columns = new String[]{
                dbHelper.KEY_ITEAMCARD_ID,
                dbHelper.KEY_ITEAMCARD_NAME,
                dbHelper.KEY_ITEAMCARD_MEASURAREUNITS,
                dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM,
                dbHelper.KEY_ITEAMCARD_ARTICULARNUM,
                dbHelper.KEY_ITEAMCARD_BARCODE
        };
        //Cursor mCursor = database.rawQuery("SELECT * FROM " + dbHelper.TABLE_ITEMCARD, null);
        //Cursor c = database.query(dbHelper.TABLE_ITEMCARD, columns, "_id=?", new String[]{itemid}, null, null, dbHelper.KEY_ITEAMCARD_NAME);
        Cursor c = database.rawQuery("SELECT * FROM itemcard WHERE _id = ? ", new String[] {String.valueOf(itemid)});
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

}
