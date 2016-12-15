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

import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.Controllers.PackInfoCont;
import com.example.olya.shop.Controllers.PackageTypeCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.PackInfo;
import com.example.olya.shop.Models.PackageType;
import com.example.olya.shop.R;

/**
 * Created by Olya on 11.12.2016.
 */

public class PackInfoActivity extends Activity {
    PackInfoCont packInfoCont;
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;
    private SimpleCursorAdapter dataAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        packInfoCont = new PackInfoCont(getApplicationContext());
        //відкриваєм бд
        packInfoCont.open();
        //створ нового користув
        PackInfo packInfo = new PackInfo(1, 1, 400);
        //додавання нов кор
        packInfoCont.addPackInfo(packInfo);
        packInfoCont.addPackInfo(new PackInfo(2,10,550));

        packInfoCont.close();

        displayListView();

    }

    private void displayListView() {

        packInfoCont = new PackInfoCont(getApplicationContext());
        packInfoCont.open();
        Cursor cursor = packInfoCont.fetchAllPacksInfo();

        // The desired columns to be bound
        String[] columns = new String[]{
                dbHelper.KEY_PACKINFO_PACKID,
                dbHelper.KEY_PACKINFO_ITEMCARDID,
                dbHelper.KEY_PACKINFO_PACKID,
                dbHelper.KEY_PACKINFO_AMOUNT
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

        packInfoCont.close();
    }
}
