package com.example.olya.shop;

        import android.app.ListActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.Toast;

        import com.example.olya.shop.DAL.DbHelper;
        import com.example.olya.shop.Models.Vendor;
        import com.example.olya.shop.ViewModels.AdditICardInfoActivity;
        import com.example.olya.shop.ViewModels.AddressActivity;
        import com.example.olya.shop.ViewModels.AddressBuyerActivity;
        import com.example.olya.shop.ViewModels.AddressVendorActivity;
        import com.example.olya.shop.ViewModels.BuyerActivity;
        import com.example.olya.shop.ViewModels.CatalogsActivity;
        import com.example.olya.shop.ViewModels.CityActivity;
        import com.example.olya.shop.ViewModels.CountryActivity;
        import com.example.olya.shop.ViewModels.IncomingActivity;
        import com.example.olya.shop.ViewModels.ItemCardActivity;
        import com.example.olya.shop.ViewModels.OutcomingActivity;
        import com.example.olya.shop.ViewModels.PackInfoActivity;
        import com.example.olya.shop.ViewModels.PackTypeActivity;
        import com.example.olya.shop.ViewModels.StreetActivity;
        import com.example.olya.shop.ViewModels.VendorActivity;
//Main file.
public class MainActivity extends ListActivity {
//declare variables
    Button btn;

    //here start program
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //decalre xml file with all lists, buttons... we need for frontend
        setContentView(R.layout.activity_main);
        //declare array with names of activities will call
        //Goods - list and information with items in our shop
        //Incoming - items that income to shop from vendors
        //Outcoming - items that outcome from shop (items that we sell to buyers)
        String[] tableNames = new String[] { "Goods", "Incoming", "Outcoming", "Catalogs"};
        //declare adapter for showing items in list from tableNames
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tableNames);
        setListAdapter(adapter);

        btn = (Button) findViewById(R.id.button4);
    }

    public void selfDestructDatabase(View view)
    {
        //delete database
        getApplicationContext().deleteDatabase(DbHelper.DATABASE_NAME);
    }

    //actions that happen if click the item of list
    @Override
    protected void onListItemClick (ListView l, View v, int position, long id) {
        String selectedValue = (String) getListAdapter().getItem(position);
        switch (selectedValue){
            case "Goods": Intent intent = new Intent(this, ItemCardActivity.class);
                startActivity(intent); break;
            case "Incoming": Intent intent2 = new Intent(this, IncomingActivity.class);
                startActivity(intent2); break;
            case "Outcoming": Intent intent3 = new Intent(this, OutcomingActivity.class);
                startActivity(intent3); break;
            case "Catalogs": Intent intent4 = new Intent(this, CatalogsActivity.class);
                startActivity(intent4); break;

            //default: Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show(); break;
        }
    }
}
