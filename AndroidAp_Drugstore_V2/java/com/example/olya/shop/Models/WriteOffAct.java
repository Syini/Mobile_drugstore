package com.example.olya.shop.Models;

import java.util.Date;

/**
 * Created by Olya on 18.12.2016.
 */

public class WriteOffAct {

        public long id;
        public long itemid;
        public long nomnum;
        public String units;
        public long amount;
        public double price_for_one;
        public double sum;
        Date date;

        public String quality;

        public WriteOffAct() {}
        public WriteOffAct(long itemid, long nomnum, String units, long amount, double price_for_one, double sum)
        {
                this.itemid = itemid;
                this.nomnum = nomnum;
                this.units = units;
                this.amount = amount;
                this.price_for_one = price_for_one;
                this.sum = sum;
                this.date = date;
        }

        public void setId(long id) { this.id = id; }
        public void setItemCardId(long itemid) { this.itemid = itemid; }
        public void setNomNum(long nomnum) { this.nomnum = nomnum; }
        public void setUnits(String units) { this.units = units; }
        public void setAmount(long amount) { this.amount = amount; }
        public void setPriceForOne(double price_for_one) { this.price_for_one = price_for_one; }
        public void setSum(double sum) { this.sum = sum; }
        public void setDate(Date date) { this.date = date; }


}
