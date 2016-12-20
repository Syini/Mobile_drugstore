package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.olya.shop.Controllers.AdditICardInfoCont;
import com.example.olya.shop.Controllers.IncomingCont;
import com.example.olya.shop.Controllers.ItemCardCont;
import com.example.olya.shop.Controllers.PackInfoCont;
import com.example.olya.shop.Controllers.PackageTypeCont;
import com.example.olya.shop.Controllers.VendorCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.AdditICardInfo;
import com.example.olya.shop.Models.Incoming;
import com.example.olya.shop.Models.ItemCard;
import com.example.olya.shop.Models.PackInfo;
import com.example.olya.shop.Models.PackageType;
import com.example.olya.shop.Models.Vendor;
import com.example.olya.shop.R;

import java.util.Date;
import java.util.List;

/**
 * Created by Olya on 14.12.2016.
 */

public class AddItemActivity extends Activity {

    private Button btn;
    private AutoCompleteTextView name_edittext;
    private EditText barcode_edittext;
    private EditText nomnum_edittext;
    private EditText artic_edittext;
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
    IncomingCont incomeCont;
    PackageTypeCont packTypeCont;
    PackInfoCont packInfoCont;
    ItemCardCont iCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item);

        btn = (Button) findViewById(R.id.btn_addrecord);

        name_edittext = (AutoCompleteTextView) findViewById(R.id.name_edittext);
        barcode_edittext = (EditText) findViewById(R.id.barcode_edittext);
        nomnum_edittext = (EditText) findViewById(R.id.nomnum_edittext);
        artic_edittext = (EditText) findViewById(R.id.artic_edittext);
        price_edittext = (EditText) findViewById(R.id.price_edittext);
        packtype_edittext = (EditText) findViewById(R.id.packtype_edittext);
        amount_edittext = (EditText) findViewById(R.id.amount_edittext);
        vendor_edittext = (EditText) findViewById(R.id.vendor_edittext);
        //date_edittext = (EditText) findViewById(R.id.date_edittext);
        showItems();
    }

    public void showItems() {
        iCont = new ItemCardCont(getApplicationContext());
        iCont.open();
        List<String> itemsNames = iCont.getItemValuesInStringList();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, itemsNames);
        name_edittext.setAdapter(adapter);
        iCont.close();

    }

    public void addItem(View view){

        if( name_edittext.getText().toString().trim().equals(""))
        {
            name_edittext.setError( "First name is required!" );

            name_edittext.setHint("please enter username");
        }
        else if (barcode_edittext.getText().toString().trim().equals(""))
        {
            barcode_edittext.setError( "Barcode is required!" );

            barcode_edittext.setHint("please enter nomnum");
        }
        else if (nomnum_edittext.getText().toString().trim().equals(""))
        {
            nomnum_edittext.setError( "nomnum is required!" );

            nomnum_edittext.setHint("please enter nomnum");
        }
        else if (artic_edittext.getText().toString().trim().equals(""))
        {
            artic_edittext.setError( "artic is required!" );

            artic_edittext.setHint("please enter articular number");
        }
        else {

            String name = name_edittext.getText().toString();
            long barcode = Long.parseLong(barcode_edittext.getText().toString());
            String nomnum = nomnum_edittext.getText().toString();
            String articnum = artic_edittext.getText().toString();
            double price = Double.parseDouble(price_edittext.getText().toString());
            String packtype = packtype_edittext.getText().toString();
            long amount = Integer.parseInt(amount_edittext.getText().toString());
            String vendor = vendor_edittext.getText().toString();
            //String date = date_edittext.getText().toString();

            CheckBox cb = (CheckBox)findViewById(R.id.checkbox1);

            ItemCard item = new ItemCard();
            //btn.setText(name);
            itemCardCont = new ItemCardCont(getApplicationContext());
            itemCardCont.open();
            //itemCardCont.findItemCardNomNum(nomnum);
            long idExistedItem = itemCardCont.findItemCard(name);
            long idExistedNomNum = itemCardCont.findItemCardNomNum(nomnum);
            long idItem = 0;
            if (idExistedItem == 0)
                idItem = itemCardCont.addItemCard(new ItemCard(name, 20, nomnum, articnum, barcode));
            else {
                if(!(idExistedNomNum == 0))
                    //idNewItemCard = itemCardCont.addItemCard(new ItemCard(name, price, idExistedNomNum, "123bc", barcode));
                    idItem = idExistedNomNum;
                /*else {
                    nomnum_edittext.setError( "Error in nomnum!" );
                    nomnum_edittext.setHint("please enter right nomnum");
                }*/
            }
            itemCardCont.close();

            additicardCont = new AdditICardInfoCont(getApplicationContext());
            additicardCont.open();
            Date date1 = new Date();
            if (idExistedItem == 0)
                additicardCont.addAdditICardInfo(new AdditICardInfo(price, idItem, date1));
            else {
                additicardCont.addAdditICardInfo(new AdditICardInfo(price, idExistedItem, date1));
                //idItem = idExistedItem;
            }
            additicardCont.close();

            packTypeCont = new PackageTypeCont(getApplicationContext());
            packTypeCont.open();
            long id_pack = packTypeCont.findPackageType(packtype);
            if (id_pack == 0)
                id_pack = packTypeCont.addPackType2(new PackageType(packtype));

            packTypeCont.close();

            packInfoCont = new PackInfoCont(getApplicationContext());
            packInfoCont.open();
            packInfoCont.addPackInfo(new PackInfo(id_pack, idItem, amount));

            packInfoCont.close();

            vendorCont = new VendorCont(getApplicationContext());
            vendorCont.open();
            long idExistedVendor = vendorCont.findVendor(vendor);
            long idVendor = 0;
            if (idExistedVendor == 0 )
                idVendor = vendorCont.addVendor(new Vendor(vendor, "drfo", "phis"));
            else idVendor = idExistedVendor;
            vendorCont.close();

            if(cb.isChecked()) {
                incomeCont = new IncomingCont(getApplicationContext());
                incomeCont.open();
                incomeCont.addIncoming(new Incoming(idVendor, date1, amount, idItem));
                incomeCont.close();
            }
            finish();

        }
    };
/*
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox1:
            if (checked)
                Toast.makeText(getApplicationContext(), "checked", Toast.LENGTH_SHORT).show();

            break;
        }
    }
*/
}
