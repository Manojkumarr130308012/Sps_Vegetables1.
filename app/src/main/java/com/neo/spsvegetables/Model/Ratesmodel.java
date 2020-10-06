package com.neo.spsvegetables.Model;

public class Ratesmodel {
//    private int imageResource;
        String product_name ;
        String code;
        String rate;
        String market_price;

    public Ratesmodel(String product_name, String code, String rate, String market_price) {
        this.product_name = product_name;
        this.code = code;
        this.rate = rate;
        this.market_price = market_price;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }
}
