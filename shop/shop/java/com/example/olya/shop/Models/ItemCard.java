package com.example.olya.shop.Models;

/**
 * Created by lily on 12/5/16.
 */

    public class ItemCard {
        public long id;
        public String name;
        public double units;
        public String nomenclNum;
        public String articNum;
        public long barCode;

        public ItemCard()
        {
        }
        public ItemCard(String name, double units, String nomenclNum, String articNum, long barCode)
        {
            this.name = name;
            this.units = units;
            this.nomenclNum = nomenclNum;
            this.articNum = articNum;
            this.barCode = barCode;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUnits(double units) {
            this.units = units;
        }

        public void setNomenclNum(String nomenclNum) {
            this.nomenclNum = nomenclNum;
        }

        public void setArticNum(String articNum) {
            this.articNum = articNum;
        }

        public void setBarCode(long barCode) {
            this.barCode = barCode;
        }


}
