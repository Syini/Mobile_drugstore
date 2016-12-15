package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.olya.shop.Models.Vendor;
import com.example.olya.shop.R;
import com.example.olya.shop.ViewModels.AdditICardInfoActivity;
import com.example.olya.shop.ViewModels.AddressActivity;
import com.example.olya.shop.ViewModels.AddressBuyerActivity;
import com.example.olya.shop.ViewModels.AddressVendorActivity;
import com.example.olya.shop.ViewModels.BuyerActivity;
import com.example.olya.shop.ViewModels.CityActivity;
import com.example.olya.shop.ViewModels.CountryActivity;
import com.example.olya.shop.ViewModels.IncomingActivity;
import com.example.olya.shop.ViewModels.ItemCardActivity;
import com.example.olya.shop.ViewModels.OutcomingActivity;
import com.example.olya.shop.ViewModels.PackInfoActivity;
import com.example.olya.shop.ViewModels.PackTypeActivity;
import com.example.olya.shop.ViewModels.StreetActivity;
import com.example.olya.shop.ViewModels.VendorActivity;

/**
 * Created by Olya on 13.12.2016.
 */

public class CatalogsActivity extends ListActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] tableNames = new String[] { "ItemCards", "AddItemCardInfo", "PackagesTypes", "PackagesInfo",
                "Countries", "Cities", "Streets", "Addresses", "VendorsAddresses", "BuyersAddresses", "Vendors", "Buyers", "Incomings",
                "Outcomings", "Waybills", "AdditInfoWaybill", "Invoice", "VendorsCountingNumbers" };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableNames);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        String selectedValue = (String) getListAdapter().getItem(position);
        switch (selectedValue){
            case "ItemCards": Intent intent = new Intent(this, ItemCardActivity.class);
                startActivity(intent); break;
            case "AddItemCardInfo": Intent intent2 = new Intent(this, AdditICardInfoActivity.class);
                startActivity(intent2); break;
            case "PackagesTypes": Intent intent3 = new Intent(this, PackTypeActivity.class);
                startActivity(intent3); break;
            case "PackagesInfo": Intent intent4 = new Intent(this, PackInfoActivity.class);
                startActivity(intent4); break;
            case "Countries": Intent intent5 = new Intent(this, CountryActivity.class);
                startActivity(intent5); break;
            case "Cities": Intent intent6 = new Intent(this, CityActivity.class);
                startActivity(intent6); break;
            case "Streets": Intent intent7 = new Intent(this, StreetActivity.class);
                startActivity(intent7); break;
            case "Addresses": Intent intent8 = new Intent(this, AddressActivity.class);
                startActivity(intent8); break;
            case "Vendors": Intent intent9 = new Intent(this, VendorActivity.class);
                startActivity(intent9); break;
            case "Buyers": Intent intent10 = new Intent(this, BuyerActivity.class);
                startActivity(intent10); break;
            case "Incomings": Intent intent11 = new Intent(this, IncomingActivity.class);
                startActivity(intent11); break;
            case "VendorsAddresses": Intent intent12 = new Intent(this, AddressVendorActivity.class);
                startActivity(intent12); break;
            case "BuyersAddresses":  Intent intent13 = new Intent(this, AddressBuyerActivity.class);
                startActivity(intent13); break;
            case "Outcomings":  Intent intent14 = new Intent(this, OutcomingActivity.class);
                startActivity(intent14); break;

            default: Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show(); break;
        }
    }
}
