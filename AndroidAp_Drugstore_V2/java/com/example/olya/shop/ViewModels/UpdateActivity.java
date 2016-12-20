package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.Controllers.PackInfoCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.R;

/**
 * Created by Olya on 16.12.2016.
 */

public class UpdateActivity extends Activity {
    private DbHelper dbHelper;
    private Context context;

    ItemCardCont itemCardCont;

    EditText id_edittext;
    EditText name_edittext;
    EditText units_edittext;
    EditText nomnum_edittext;
    EditText articnum_edittext;
    EditText barcode_edittext;

    long itemId = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_item2);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            itemId = extras.getLong("key");
            //The key argument here must match that used in the other activity
        }

        //id_edittext = (EditText) findViewById(R.id.id);
        name_edittext = (EditText) findViewById(R.id.nomnum);
        units_edittext = (EditText) findViewById(R.id.nomnum1);
        nomnum_edittext = (EditText) findViewById(R.id.nomnum2);
        articnum_edittext = (EditText) findViewById(R.id.artic_num);
        barcode_edittext = (EditText) findViewById(R.id.bar_code);

        displayList();
        fillTable(itemId);
    }

    public void displayList(){
        itemCardCont = new ItemCardCont(getApplicationContext());
        itemCardCont.open();

        Cursor cursor = itemCardCont.getItemCard(itemId);
        if (cursor.moveToFirst()) // data?
            //System.out.println(cursor.getString(cursor.getColumnIndex("title"));
        {
            //long itemid = cursor.getLong(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            double units = cursor.getDouble(cursor.getColumnIndex("measureUnits"));
            String nomnum = cursor.getString(cursor.getColumnIndex("nomenclNum"));
            String articnum = cursor.getString(cursor.getColumnIndex("articNum"));
            long barCode = cursor.getLong(cursor.getColumnIndex("barCode"));

            //id_edittext.setText(""+id);
            name_edittext.setText(name);
            units_edittext.setText(""+units);
            nomnum_edittext.setText(""+nomnum);
            articnum_edittext.setText(""+articnum);
            barcode_edittext.setText(""+barCode);

            //Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
        }
        cursor.close(); // th
    }

    public void updateItem(View view) {
        long id = itemId;
        String name = name_edittext.getText().toString();
        double units = Double.parseDouble(units_edittext.getText().toString());
        String nomnum = nomnum_edittext.getText().toString();
        String articnum = articnum_edittext.getText().toString();
        long barCode = Long.parseLong(barcode_edittext.getText().toString());

        itemCardCont = new ItemCardCont(getApplicationContext());
        itemCardCont.open();
        itemCardCont.updateItemCard(itemId, name, 555, nomnum, articnum, barCode);
        itemCardCont.close();
        finish();
    }

    public void fillTable(long packid) {
        context = this;

        // Reference to TableLayout
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        // Add header row
        TableRow rowHeader = new TableRow(context);
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"));
        rowHeader.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
        String[] headerText = {"AMOUNT", "PACK NAME"};
        for (String c : headerText) {
            TextView tv = new TextView(this);
            tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT));
            tv.setGravity(Gravity.CENTER);
            tv.setTextSize(18);
            tv.setPadding(5, 5, 5, 5);
            tv.setText(c);
            rowHeader.addView(tv);
        }
        tableLayout.addView(rowHeader);

        PackInfoCont packCont = new PackInfoCont(getApplicationContext());
        packCont.open();
        Cursor cur = packCont.fetchAllPacksInfoById(packid);
        if (cur.getCount() > 0) {
            //while (cur.moveToNext()) {
                // Read columns data
                long id = cur.getLong(cur.getColumnIndex("amount"));
                String pack_name = cur.getString(cur.getColumnIndex("packName"));
                // dara rows
                TableRow row = new TableRow(context);
                row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                String[] colText={id+"",pack_name+""};
                for(String text:colText) {
                    TextView tv = new TextView(this);
                    tv.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));
                    tv.setGravity(Gravity.CENTER);
                    tv.setTextSize(16);
                    tv.setPadding(5, 5, 5, 5);
                    tv.setText(text);
                    row.addView(tv);
                }
                tableLayout.addView(row);
            }
    }


}
