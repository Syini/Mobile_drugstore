package com.example.olya.shop.ViewModels;

import android.app.Activity;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.example.olya.shop.Controllers.BuyerCont;
import com.example.olya.shop.Controllers.VendorCont;
import com.example.olya.shop.Models.Buyer;
import com.example.olya.shop.Models.Vendor;
import com.example.olya.shop.R;

/**
 * Created by Olya on 12.12.2016.
 */

public class BuyerActivity extends Activity {
    BuyerCont buyerCont;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card);

        //getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
        buyerCont = new BuyerCont(getApplicationContext());
        //відкриваєм бд
        buyerCont.open();
        //створ нового користув
        Buyer buyer = new Buyer("Com3", "EDRPOU", "lawyerPer");
        //додавання нов кор
        buyerCont.addBuyer(buyer);
        buyerCont.addBuyer(new Buyer("Com4", "EDRPOU", "lawyerPer"));

        buyerCont.close();
    }
}