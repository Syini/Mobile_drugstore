package com.example.olya.shop.DAL;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lily on 12/5/16.
 */

    //file with database info such as name, version
    //tables names, names of columns
public class DbHelper extends SQLiteOpenHelper {

    //db info
    private static final int DATABASE_VERSION = 4;
    public static final String DATABASE_NAME = "shop";
    //-------------------------------------------

    //info about table itemcard
    public static final String TABLE_ITEMCARD = "itemcard";
    //поля таблиці
    public static final String KEY_ITEAMCARD_ID = "_id";
    public static final String KEY_ITEAMCARD_NAME = "name";
    public static final String KEY_ITEAMCARD_MEASURAREUNITS = "measureUnits";
    public static final String KEY_ITEAMCARD_NOMENCLATURENUM = "nomenclNum";
    public static final String KEY_ITEAMCARD_ARTICULARNUM = "articNum";
    public static final String KEY_ITEAMCARD_BARCODE = "barCode";
    //-----------------

    //info about table additional itemcard information
    public static final String TABLE_ADDICARDINFO = "addit_icard_info";

    public static final String KEY_ADDICARDINFO_ID = "_id";
    public static final String KEY_ADDICARDINFO_PRICE = "_price";
    public static final String KEY_ADDICARDINFO_ITEMCARDID = "itemCardID";
    public static final String KEY_ADDICARDINFO_DATE = "date";
    //-----------------

    //info about table package types info
    public static final String TABLE_PACKTYPE = "packagetype";

    public static final String KEY_PACKTYPE_ID = "pack_id";
    public static final String KEY_PACKTYPE_NAME = "packName";
    //-----------------

    //info about table package info
    public static final String TABLE_PACKINFO = "packageinfo";

    public static final String KEY_PACKINFO_ID = "_id";
    public static final String KEY_PACKINFO_PACKID = "pack_id";
    public static final String KEY_PACKINFO_ITEMCARDID = "itemcard_id";
    public static final String KEY_PACKINFO_AMOUNT = "amount";
    //-----------------

    //info about table vendor
    public static  final String TABLE_VENDOR = "vendor";

    public static final String KEY_VENDOR_ID = "_id";
    public static final String KEY_VENDOR_COMPANYNAMEFOP = "companyname_fop";
    public static final String KEY_VENDOR_EDRPOU_DRFO = "edrpou_drfo";
    public static final String KEY_VENDOR_SIGN = "sign";
    public static final String KEY_VENDOR_ADDRID = "address_id";
    //-----------------

    //-----------------
    public static final String TABLE_BUYER = "buyer";

    public static final String KEY_BUYER_ID = "_id";
    public static final String KEY_BUYER_COMPANYNAMEFOP = "companyname_fop";
    public static final String KEY_BUYER_EDRPOU_DRFO = "edrpou_drfo";
    public static final String KEY_BUYER_SIGN = "sign";
    public static final String KEY_BUYER_ADDRID = "address_id";
    //-----------------

    //-----------------
    public static final String TABLE_COUNTRY = "country";

    public static final String KEY_COUNTRY_ID = "_id";
    public static final String KEY_COUNTRY_NAME = "name";
    //-----------------

    //-----------------
    public static final String TABLE_CITY = "city";

    public static final String KEY_CITY_ID = "_id";
    public static final String KEY_CITY_NAME = "name";
    //-----------------

    //-----------------
    public static final String TABLE_STREET = "street";

    public static final String KEY_STREET_ID = "_id";
    public static final String KEY_STREET_NAME = "name";
    //-----------------

    //-----------------
    public static final String TABLE_ADDRESS = "address";

    public static final String KEY_ADDRESS_ID = "_id";
    public static final String KEY_ADDRESS_COUNTRYID = "country_id";
    public static final String KEY_ADDRESS_STREETID = "street_id";
    public static final String KEY_ADDRESS_CITYID = "city_id";
    public static final String KEY_ADDRESS_HOUSENUM = "house_num";
    //-----------------

    //-----------------
    public static final String TABLE_INCOMING = "incoming";

    public static final String KEY_INCOMING_ID = "_id";
    public static final String KEY_INCOMING_VENDORID = "vendor_id";
    public static final String KEY_INCOMING_DATE = "date";
    public static final String KEY_INCOMING_AMOUNT = "amount";
    public static final String KEY_INCOMING_ITEMCARDID = "itemcardid";
    //-----------------

    //-----------------
    public static final String TABLE_OUTCOMING = "outcoming";

    public static final String KEY_OUTCOMING_ID = "_id";
    public static final String KEY_OUTCOMING_BUYERID = "buyer_id";
    public static final String KEY_OUTCOMING_DATE = "date";
    public static final String KEY_OUTCOMING_AMOUNT = "amount";
    public static final String KEY_OUTCOMING_ITEMCARDID = "itemcardid";

    //-----------------

    //-----------------
    public static final String TABLE_ADDRESSVENDOR = "address_vendor";

    public static final String KEY_ADDRESSVENDOR_ID = "_id";
    public static final String KEY_ADDRESSVENDOR_VENDORID = "vendor_id";
    public static final String KEY_ADDRESSVENDOR_ADDRESSID = "address_id";
    //-----------------

    //-----------------
    public static final String TABLE_ADDRESSBUYER = "address_buyer";

    public static final String KEY_ADDRESSBUYER_ID = "_id";
    public static final String KEY_ADDRESSBUYER_BUYERID = "buyer_id";
    public static final String KEY_ADDRESSBUYER_ADDRESSID = "address_id";
    //-----------------

/*
    //-----------------
    public static final String TABLE_WRITEOFFACT = "write_off_act";

    public static final String KEY_WRITEOFFACT_ID = "_id";
    public static final String KEY_WRITEOFFACT_ITEMID = "item_id";
    public static final String KEY_WRITEOFFACT_ITEMNONUM = "itemnomnum";
    public static final String KEY_WRITEOFFACT_ITEMUNITS = "units";
    public static final String KEY_WRITEOFFACT_AMOUNT = "amount";
    public static final String KEY_WRITEOFFACT_PRICE = "price_for_one";
    public static final String KEY_WRITEOFFACT_SUM = "sum";
    public static final String KEY_WRITEOFFACT_DATE = "date";
    //-----------------
*/
    //here queries for creating tables
    //-----------------
    private static final String CREATE_TABLE_ITEAMCARD = "CREATE TABLE "
            + TABLE_ITEMCARD + "(" + KEY_ITEAMCARD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ITEAMCARD_NAME + " TEXT," + KEY_ITEAMCARD_MEASURAREUNITS + " REAL," + KEY_ITEAMCARD_NOMENCLATURENUM
            + " TEXT NOT NULL," + KEY_ITEAMCARD_ARTICULARNUM + " TEXT," + KEY_ITEAMCARD_BARCODE + " TEXT" + ")";

    //-----------------
    private static final String CREATE_TABLE_ADDICARDINFO = "CREATE TABLE "
            + TABLE_ADDICARDINFO + "(" + KEY_ADDICARDINFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ADDICARDINFO_PRICE + " REAL," + KEY_ADDICARDINFO_ITEMCARDID + " INTEGER,"
            + KEY_ADDICARDINFO_DATE + " TEXT," + " FOREIGN KEY (" + KEY_ADDICARDINFO_ITEMCARDID + ") REFERENCES "
            + TABLE_ITEMCARD + "(" + KEY_ITEAMCARD_ID + ") ON DELETE CASCADE);";

    private static final String CREATE_TABLE_PACKTYPE = "CREATE TABLE "
            + TABLE_PACKTYPE + "(" + KEY_PACKTYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PACKTYPE_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_PACKINFO = "CREATE TABLE "
            + TABLE_PACKINFO + "(" + KEY_PACKINFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_PACKINFO_PACKID + " INTEGER," + KEY_PACKINFO_ITEMCARDID + " INTEGER,"
            + KEY_PACKINFO_AMOUNT + " INTEGER," + " FOREIGN KEY (" + KEY_PACKINFO_PACKID + ") REFERENCES "
            + TABLE_PACKTYPE + "(" + KEY_PACKTYPE_ID + ") ON DELETE CASCADE," + " FOREIGN KEY ("
            + KEY_PACKINFO_ITEMCARDID + ") REFERENCES " + TABLE_ITEMCARD + "(" + KEY_ITEAMCARD_ID + ") ON DELETE CASCADE);";

    private static final String CREATE_TABLE_VENDOR = "CREATE TABLE "
            + TABLE_VENDOR + "(" + KEY_VENDOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_VENDOR_COMPANYNAMEFOP + " TEXT," + KEY_VENDOR_EDRPOU_DRFO + " TEXT,"
            + KEY_VENDOR_SIGN + " TEXT" + ")";

    private static final String CREATE_TABLE_BUYER = "CREATE TABLE "
            + TABLE_BUYER + "(" + KEY_BUYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_BUYER_COMPANYNAMEFOP + " TEXT," + KEY_BUYER_EDRPOU_DRFO + " TEXT,"
            + KEY_BUYER_SIGN + " TEXT" + ")";

    private static final String CREATE_TABLE_COUNTRY = "CREATE TABLE "
            + TABLE_COUNTRY + "(" + KEY_COUNTRY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_COUNTRY_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_CITY = "CREATE TABLE "
            + TABLE_CITY + "(" + KEY_CITY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_CITY_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_STREET = "CREATE TABLE "
            + TABLE_STREET + "(" + KEY_STREET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_STREET_NAME + " TEXT" + ")";

    private static final String CREATE_TABLE_ADDRESS = "CREATE TABLE "
            + TABLE_ADDRESS + "(" + KEY_ADDRESS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ADDRESS_COUNTRYID + " INTEGER," + KEY_ADDRESS_CITYID + " INTEGER,"
            + KEY_ADDRESS_STREETID + " INTEGER," + KEY_ADDRESS_HOUSENUM + " TEXT,"
            + " FOREIGN KEY (" + KEY_ADDRESS_COUNTRYID + ") REFERENCES " + TABLE_COUNTRY + "("
            + KEY_COUNTRY_ID + ") ON DELETE CASCADE," + " FOREIGN KEY (" + KEY_ADDRESS_CITYID
            + ") REFERENCES " + TABLE_CITY + "(" + KEY_CITY_ID + ") ON DELETE CASCADE,"
            + " FOREIGN KEY (" + KEY_ADDRESS_STREETID + ") REFERENCES " + TABLE_STREET + "("
            + KEY_STREET_ID + ") ON DELETE CASCADE);";

    private static final String CREATE_TABLE_INCOMING = "CREATE TABLE "
            + TABLE_INCOMING + "(" + KEY_INCOMING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_INCOMING_VENDORID + " INTEGER," + KEY_INCOMING_DATE + " TEXT," + KEY_INCOMING_AMOUNT
            + " INTEGER," + KEY_INCOMING_ITEMCARDID + " INTEGER," + " FOREIGN KEY (" + KEY_INCOMING_VENDORID + ") REFERENCES "
            + TABLE_VENDOR + "(" + KEY_VENDOR_ID + ") ON DELETE CASCADE,"
            + " FOREIGN KEY (" + KEY_INCOMING_ITEMCARDID + ") REFERENCES "
            + TABLE_ITEMCARD + "(" + KEY_ITEAMCARD_ID + ") ON DELETE CASCADE);";

    private static final String CREATE_TABLE_OUTCOMING = "CREATE TABLE "
            + TABLE_OUTCOMING + "(" + KEY_OUTCOMING_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_OUTCOMING_BUYERID + " INTEGER," + KEY_OUTCOMING_DATE + " TEXT," + KEY_OUTCOMING_AMOUNT
            + " INTEGER," + KEY_OUTCOMING_ITEMCARDID + " INTEGER," + " FOREIGN KEY (" + KEY_OUTCOMING_BUYERID + ") REFERENCES "
            + TABLE_BUYER + "(" + KEY_BUYER_ID + ") ON DELETE CASCADE);";

    private static final String CREATE_TABLE_ADDRESSVENDOR = "CREATE TABLE "
            + TABLE_ADDRESSVENDOR + "(" + KEY_ADDRESSVENDOR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ADDRESSVENDOR_VENDORID + " INTEGER," + KEY_ADDRESSVENDOR_ADDRESSID + " INTEGER,"
            + " FOREIGN KEY (" + KEY_ADDRESSVENDOR_VENDORID + ") REFERENCES " + TABLE_VENDOR
            +  "(" + KEY_VENDOR_ID + ") ON DELETE CASCADE," + " FOREIGN KEY (" + KEY_ADDRESSVENDOR_ADDRESSID
            + ") REFERENCES " + TABLE_ADDRESS + "(" + KEY_ADDRESS_ID + ") ON DELETE CASCADE);";

    private static final String CREATE_TABLE_ADDRESSBUYER = "CREATE TABLE "
            + TABLE_ADDRESSBUYER + "(" + KEY_ADDRESSBUYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + KEY_ADDRESSBUYER_BUYERID + " INTEGER," + KEY_ADDRESSBUYER_ADDRESSID + " INTEGER,"
            + " FOREIGN KEY (" + KEY_ADDRESSBUYER_BUYERID + ") REFERENCES " + TABLE_BUYER
            +  "(" + KEY_BUYER_ID + ") ON DELETE CASCADE," + " FOREIGN KEY (" + KEY_ADDRESSBUYER_ADDRESSID
            + ") REFERENCES " + TABLE_ADDRESS + "(" + KEY_ADDRESS_ID + ") ON DELETE CASCADE);";

    //connection with db
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //create tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ITEAMCARD);
        db.execSQL(CREATE_TABLE_ADDICARDINFO);
        db.execSQL(CREATE_TABLE_PACKTYPE);
        db.execSQL(CREATE_TABLE_PACKINFO);
        db.execSQL(CREATE_TABLE_VENDOR);
        db.execSQL(CREATE_TABLE_BUYER);
        db.execSQL(CREATE_TABLE_COUNTRY);
        db.execSQL(CREATE_TABLE_CITY);
        db.execSQL(CREATE_TABLE_STREET);
        db.execSQL(CREATE_TABLE_ADDRESS);
        db.execSQL(CREATE_TABLE_INCOMING);
        db.execSQL(CREATE_TABLE_ADDRESSVENDOR);
        db.execSQL(CREATE_TABLE_ADDRESSBUYER);
        db.execSQL(CREATE_TABLE_OUTCOMING);
    }

    //remake db
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //delete old db
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ITEAMCARD);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ADDICARDINFO);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PACKTYPE);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_PACKINFO);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_VENDOR);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_BUYER);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_COUNTRY);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_CITY);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_STREET);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ADDRESS);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_INCOMING);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ADDRESSVENDOR);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_ADDRESSBUYER);
        db.execSQL("DROP TABLE IF EXISTS " + CREATE_TABLE_OUTCOMING);
        //створ нові
        onCreate(db);
    }


}