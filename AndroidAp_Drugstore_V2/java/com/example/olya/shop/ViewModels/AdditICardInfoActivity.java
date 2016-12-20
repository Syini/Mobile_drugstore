package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.olya.shop.Controllers.AdditICardInfoCont;
import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.ItemCard;
import com.example.olya.shop.R;

import java.util.Date;

/**
 * Created by lily on 12/6/16.
 */

public class AdditICardInfoActivity extends Activity {
    AdditICardInfoCont additICardInfoCont;
    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.addit_icard_info);
/*
        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        additICardInfoCont = new AdditICardInfoCont(getApplicationContext());
        //відкриваєм бд
        additICardInfoCont.open();

        Date date = new Date();
        AdditICardInfo addICardInfo = new AdditICardInfo(560.20, 1, date);
        //додавання нов кор
        additICardInfoCont.addAdditICardInfo(addICardInfo);
        additICardInfoCont.addAdditICardInfo(new AdditICardInfo(30000.5, 2, date));

        additICardInfoCont.close();
*/
        displayListView();

    }

    private void displayListView() {

        additICardInfoCont = new AdditICardInfoCont(getApplicationContext());
        additICardInfoCont.open();
        Cursor cursor = additICardInfoCont.fetchAllAddItemInfo();

        // The desired columns to be bound
        String[] columns = new String[]{
                //dbHelper.KEY_ITEAMCARD_ID,
                dbHelper.KEY_ADDICARDINFO_ID,
                dbHelper.KEY_ADDICARDINFO_PRICE,
                dbHelper.KEY_ADDICARDINFO_DATE

        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.nom_num,
                R.id.units,
                R.id.name
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.additinfo_in_list,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
    }
}