package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.R;

/**
 * Created by lily on 12/6/16.
 */

public class ItemCardActivity extends Activity {
    ItemCardCont itemCardCont;
    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;

    private Button btn;
    private ListView lv;

    @Override
    public void onResume() {  // After a pause OR at startup
        super.onResume();
        displayListView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_list);
        btn = (Button) findViewById(R.id.button4);
        lv = (ListView) findViewById(R.id.listView1);

        lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lv.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
        long idPass = 0;

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                Toast.makeText(getApplicationContext(),
                        id + "countryCode", Toast.LENGTH_SHORT).show();
                idPass = id;
                // Here you can do something when items are selected/de-selected,
                // such as update the title in the CAB
                }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
              // Respond to clicks on the actions in the CAB
                switch (item.getItemId()) {
                case R.id.edit:
                {
                    Intent intent = new Intent(getApplicationContext(), UpdateActivity.class);
                    intent.putExtra("key", idPass);
                    startActivity(intent);
                    mode.finish(); // Action picked, so close the CAB
                }
                        return true;
                    case R.id.delete:
                    {
                        itemCardCont = new ItemCardCont(getApplicationContext());
                        //відкриваєм бд
                        itemCardCont.open();
                        itemCardCont.deleteItemCard(idPass);
                        itemCardCont.close();
                        displayListView();
                        mode.finish(); // Action picked, so close the CAB
                    }
                default:
                    return false;
                    }
                }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // Inflate the menu for the CAB
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(R.menu.menu, menu);
                return true;
                }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
            // Here you can make any necessary updates to the activity when
            // the CAB is removed. By default, selected items are deselected/unchecked.
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                // Here you can perform updates to the CAB due to
                // an invalidate() request
                return false;
                }
        });

        itemCardCont = new ItemCardCont(getApplicationContext());
        //відкриваєм бд
        itemCardCont.open();
        itemCardCont.getItemCards();
        itemCardCont.close();
        //Generate ListView from SQLite Database
        displayListView();

    }

    public void addItem(View view) {
        Intent intent3 = new Intent(this, AddItemActivity.class);
        startActivity(intent3);
    };

    private void displayListView() {

        itemCardCont = new ItemCardCont(getApplicationContext());
        itemCardCont.open();
        Cursor cursor = itemCardCont.fetchAllItems();

        // The desired columns to be bound
        String[] columns = new String[]{
                //dbHelper.KEY_ITEAMCARD_ID,
                dbHelper.KEY_ITEAMCARD_NAME,
                dbHelper.KEY_ITEAMCARD_MEASURAREUNITS,
                dbHelper.KEY_ITEAMCARD_NOMENCLATURENUM,
                dbHelper.KEY_ITEAMCARD_BARCODE

        };

        // the XML defined views which the data will be bound to
        int[] to = new int[]{
                R.id.id,
                R.id.name,
                //R.id.artic_num,
                R.id.units,
                R.id.nom_num

        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.item_in_list,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);

        //registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
                // Get the state's capital from this row in the database.
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow(dbHelper.KEY_ITEAMCARD_ID));
                //Toast.makeText(getApplicationContext(),
                  //      countryCode, Toast.LENGTH_SHORT).show();

                //registerForContextMenu(listView);

            }
        });


/*
        itemCardCont.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        Toast.makeText(getApplicationContext(),
                "id = " + v.getId(), Toast.LENGTH_SHORT).show();
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
        */

    }
}