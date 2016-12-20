package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.olya.shop.Controllers.BuyerCont;
import com.example.olya.shop.Controllers.IncomingCont;
import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.Controllers.VendorCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.Incoming;
import com.example.olya.shop.R;

import java.util.Date;
import java.util.List;

/**
 * Created by Olya on 20.12.2016.
 */

public class AddOutcomingActivity extends Activity {
    //об'єкт для доступу до таблиць
    private SQLiteDatabase database;
    //об'єкт для доступу до бд
    private DbHelper dbHelper;
    ItemCardCont iCont;
    VendorCont venCont;
    ArrayAdapter<String> adapter4;
    IncomingCont incCont;

    AutoCompleteTextView item;
    Spinner buyer;
    EditText date, amount;


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_outcoming);


        //item = (AutoCompleteTextView) findViewById(R.id.spinner_itemid);
        item = (AutoCompleteTextView) findViewById(R.id.spinner_itemid);
        buyer = (Spinner) findViewById(R.id.spinner_buyerid);
        date = (EditText) findViewById(R.id.date);
        amount = (EditText) findViewById(R.id.amount_edittext);
        item.setThreshold(1);

        iCont = new ItemCardCont(getApplicationContext());
        //відкриваєм бд
        iCont.open();
        List<String> itemsNames = iCont.getItemValuesInStringList();
        //List<String> itemsNames = iCont.getItemsNames();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, itemsNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        item.setAdapter(adapter);

        iCont.close();

        BuyerCont buyerCont;
        buyerCont = new BuyerCont(getApplicationContext());
        buyerCont.open();
        List<String> vendorsNames = buyerCont.getBuyersNames();
        ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1, vendorsNames);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        buyer.setAdapter(adapter3);

    }

    public void addOutcoming(View view) {
        String itemaname_input = item.getText().toString();
        iCont.open();
        long iditem_input = iCont.findItemCard(itemaname_input);
        iCont.close();

        String text_input = buyer.getSelectedItem().toString();
        //String amount = amount.getText().toString();
        long amount_input = Long.parseLong(amount.getText().toString());
        IncomingCont incCont = new IncomingCont(getApplicationContext());
        incCont.open();
        Date date = new Date();
        incCont.addIncoming(new Incoming(1, date, amount_input, 1));
        incCont.close();
        finish();
    }
}