package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;

import com.example.olya.shop.Controllers.PackageTypeCont;
import com.example.olya.shop.DAL.DbHelper;
import com.example.olya.shop.Models.PackageType;
import com.example.olya.shop.R;

/**
 * Created by lily on 12/10/16.
 */

public class PackTypeActivity extends Activity {
    PackageTypeCont packTypeCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        packTypeCont = new PackageTypeCont(getApplicationContext());
        //відкриваєм бд
        packTypeCont.open();
        //створ нового користув
        PackageType packageType = new PackageType("bottle");
        //додавання нов кор
        packTypeCont.addPackType(packageType);
        packTypeCont.addPackType(new PackageType("pack"));

        packTypeCont.close();

    }
}
