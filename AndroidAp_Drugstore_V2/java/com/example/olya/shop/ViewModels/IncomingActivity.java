package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.olya.shop.Controllers.IncomingCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        displayListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        displayListView();

    }

    public void addItem(View view) {
        Intent i = new Intent(this, AddIncomingActivity.class);
        startActivity(i);
    }
        private void displayListView() {

        incomingCont  = new IncomingCont(getApplicationContext());
        incomingCont.open();
        Cursor cursor = incomingCont.fetchAllIncomings();

        // The desired columns to be bound
        String[] columns = new String[]{
                dbHelper.KEY_VENDOR_COMPANYNAMEFOP,
                dbHelper.KEY_INCOMING_AMOUNT,
                dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM,
                dbHelper.KEY_ITEAMCARD_NAME

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