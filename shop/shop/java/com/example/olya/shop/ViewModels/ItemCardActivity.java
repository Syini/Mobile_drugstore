package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.ItemCard;
import com.example.olya.shop.R;

import java.util.List;

import static com.example.olya.shop.R.id.button4;

/**
 * Created by lily on 12/6/16.
 */

public class ItemCardActivity extends Activity {
    ItemCardCont itemCardCont;
    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;

    private Button btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);
        btn = (Button) findViewById(R.id.button4);

        itemCardCont = new ItemCardCont(getApplicationContext());
        //відкриваєм бд
        itemCardCont.open();
        //створ нового користув
        //ItemCard itemCard = new ItemCard("Dockers", 200.5, "1111", "123ab", 123);
        //додавання нов кор
        //itemCardCont.addItemCard(itemCard);
        //itemCardCont.addItemCard(new ItemCard("KitKat", 600.7, "2222", "123bc", 234));
        itemCardCont.getItemCards();

        itemCardCont.close();
        //Generate ListView from SQLite Database
        displayListView();

    }

    public void addItem(View view){
        Intent intent3 = new Intent(this, AddItemActivity.class);
        startActivity(intent3);
    };

    private void displayListView() {

        itemCardCont = new ItemCardCont(getApplicationContext());
        itemCardCont.open();
        Cursor cursor = itemCardCont.fetchAllCountries();

        // The desired columns to be bound
        String[] columns = new String[]{
                dbHelper.KEY_ITEAMCARD_ID,
                dbHelper.KEY_ITEAMCARD_NAME,
                dbHelper.KEY_ITEAMCARD_MEASURAREUNITS,
                dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM

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
                registerForContextMenu(listView);

            }
        });


        itemCardCont.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if (v.getId()==R.id.listView1) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch(item.getItemId()) {
            case R.id.add:
                // add stuff here
                return true;
            case R.id.edit:
                // edit stuff here
                return true;
            case R.id.delete:
                // remove stuff here
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
