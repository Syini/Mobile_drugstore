package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.olya.shop.Controllers.CountryCont;
import com.example.olya.shop.Controllers.IncomingCont;
import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Country;
import com.example.olya.shop.Models.Incoming;
import com.example.olya.shop.R;

import java.util.Date;

/**
 * Created by Olya on 12.12.2016.
 */

public class IncomingActivity extends Activity {
    IncomingCont incomingCont;
    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        incomingCont = new IncomingCont(getApplicationContext());
        //відкриваєм бд
        incomingCont.open();

        Date date = new Date();
        //створ нового користув
        Incoming incoming = new Incoming(1, date, 20, 1);
        //додавання нов кор
        incomingCont.addIncoming(incoming);
        incomingCont.addIncoming(new Incoming(10, date, 440, 1));
        incomingCont.addIncoming(new Incoming(2, date, 53400, 2));
        incomingCont.addIncoming(new Incoming(2, date, 120, 10));

        incomingCont.close();
        displayListView();
    }
    private void displayListView() {

        incomingCont  = new IncomingCont(getApplicationContext());
        incomingCont.open();
        Cursor cursor = incomingCont.fetchAllIncomings();

        // The desired columns to be bound
        String[] columns = new String[]{
                dbHelper.KEY_INCOMING_ID,
                dbHelper.KEY_INCOMING_VENDORID,
                dbHelper.KEY_INCOMING_AMOUNT,
                dbHelper.KEY_INCOMING_DATE

        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.id,
                R.id.name,
                R.id.units,
                R.id.nom_num

        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.item_card_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the state's capital from this row in the database.
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow("name"));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

            }
        });

        incomingCont.close();
    }
}