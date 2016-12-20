package com.example.olya.shop.Models;

/**
 * Created by Olya on 12.12.2016.
 */

public class Buyer {
    public long id;
    //FOP - name, surnmae of phisical person
    public String companyName_FOP;
    public String EDRPOU_DRFO;
    public String sign;
    public long address_id;

    public Buyer() {}

    public Buyer(String companyName_FOP, String EDRPOU_DRFO, String sign)
    {
        this.companyName_FOP = companyName_FOP;
        this.EDRPOU_DRFO = EDRPOU_DRFO;
        this.sign = sign;

    }

    public void setId(long id) {
        this.id = id;
    }
    public void setCompanyName_FOP(String companyName_FOP) { this.companyName_FOP = companyName_FOP; }
    public void setEDRPOU_DRFO(String EDRPOU_DRFO) { this.EDRPOU_DRFO = EDRPOU_DRFO; }
    public void setSign(String sign) { this.sign = sign; }

}

