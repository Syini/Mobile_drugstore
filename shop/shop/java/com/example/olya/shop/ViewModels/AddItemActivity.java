package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import com.example.olya.shop.Controllers.AdditICardInfoCont;
import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.Controllers.VendorCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.ItemCard;
import com.example.olya.shop.Models.Vendor;
import com.example.olya.shop.R;

import java.util.Date;

/**
 * Created by Olya on 14.12.2016.
 */

public class AddItemActivity extends Activity {

    private Button btn;
    private EditText name_edittext;
    private EditText barcode_edittext;
    private EditText price_edittext;
    private EditText packtype_edittext;
    private EditText amount_edittext;
    private EditText vendor_edittext;
    private EditText date_edittext;


    private DbHelper dbHelper;
    private SQLiteDatabase database;
    private SimpleCursorAdapter dataAdapter;

    ItemCardCont itemCardCont;
    AdditICardInfoCont additicardCont;
    VendorCont vendorCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        btn = (Button) findViewById(R.id.btn_addrecord);

        name_edittext = (EditText) findViewById(R.id.name_edittext);
        barcode_edittext = (EditText) findViewById(R.id.barcode_edittext);
        price_edittext = (EditText) findViewById(R.id.price_edittext);
        packtype_edittext = (EditText) findViewById(R.id.packtype_edittext);
        amount_edittext = (EditText) findViewById(R.id.amount_edittext);
        vendor_edittext = (EditText) findViewById(R.id.vendor_edittext);
        date_edittext = (EditText) findViewById(R.id.date_edittext);
    }
    public void addItem(View view){

        String name = name_edittext.getText().toString();
        int barcode = Integer.parseInt(barcode_edittext.getText().toString());
        double price = Double.parseDouble(price_edittext.getText().toString());
        String packtype = packtype_edittext.getText().toString();
        String amount = amount_edittext.getText().toString();
        String vendor = vendor_edittext.getText().toString();
        String date = date_edittext.getText().toString();

        ItemCard item = new ItemCard();
        //btn.setText(name);
        itemCardCont = new ItemCardCont(getApplicationContext());
        itemCardCont.open();
        long id2 = itemCardCont.findItemCard(name);
        long id = 0;
        if (id2 == 0)
            id = itemCardCont.addItemCard(new ItemCard(name, price, "2222", "123bc", barcode));
        itemCardCont.close();

        additicardCont = new AdditICardInfoCont(getApplicationContext());
        additicardCont.open();
        Date date1 = new Date();
        if (id2 == 0)
            additicardCont.addAdditICardInfo(new AdditICardInfo(price, id, date1));
            else additicardCont.addAdditICardInfo(new AdditICardInfo(price, id2, date1));
        additicardCont.close();

        vendorCont = new VendorCont(getApplicationContext());
        vendorCont.open();
        vendorCont.addVendor(new Vendor(vendor, "drfo", "phis"));
        vendorCont.close();
    };

}
