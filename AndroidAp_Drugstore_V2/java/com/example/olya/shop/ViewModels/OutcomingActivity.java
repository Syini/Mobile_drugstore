package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.olya.shop.Controllers.BuyerCont;
import com.example.olya.shop.Controllers.IncomingCont;
import com.example.olya.shop.Controllers.OutcomingCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Buyer;
import com.example.olya.shop.Models.Outcoming;
import com.example.olya.shop.R;

import java.util.Date;

/**
 * Created by Olya on 12.12.2016.
 */

public class OutcomingActivity extends Activity {
    OutcomingCont outcomingCont;
    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);

        outcomingCont = new OutcomingCont(getApplicationContext());
        outcomingCont.open();

        Date date = new Date();
        //створ нового користув
        Outcoming outcoming = new Outcoming(1, date, 20, 1);
        //додавання нов кор
        outcomingCont.addOutcoming(outcoming);
        outcomingCont.addOutcoming(new Outcoming(2, date, 440, 2));

        outcomingCont.close();

        displayListView();

    }

    private void displayListView() {

        outcomingCont = new OutcomingCont(getApplicationContext());
        outcomingCont.open();
        Cursor cursor = outcomingCont.fetchAllOutcomings();

        // The desired columns to be bound
        String[] columns = new String[]{
                dbHelper.KEY_OUTCOMING_ID,
                dbHelper.KEY_OUTCOMING_AMOUNT,
                dbHelper.KEY_OUTCOMING_DATE,
                dbHelper.KEY_OUTCOMING_ID

        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.vendorid,
                R.id.amount,
                R.id.date,
                R.id.itemcardid

        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.incoming_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);
        outcomingCont.close();
    }

    public void addItem(View view) {
        Intent i = new Intent(this, AddOutcomingActivity.class);
        startActivity(i);
    }
}