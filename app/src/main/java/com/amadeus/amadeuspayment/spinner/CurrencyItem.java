package com.amadeus.amadeuspayment.spinner;

public class CurrencyItem {

    private String mCurrencyName;
    private int mCurrencyImage;

    public CurrencyItem(String currencyName, int currencyImage) {
        mCurrencyName = currencyName;
        mCurrencyImage = currencyImage;
    }

    public String getCurrencyName() {
        return mCurrencyName;
    }

    public int getCurrencyImage() {
        return mCurrencyImage;
    }
}
