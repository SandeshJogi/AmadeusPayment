package com.amadeus.amadeuspayment.spinner;

import android.content.ContentResolver;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.amadeus.amadeuspayment.R;

import java.util.ArrayList;

public class CurrencyAdapter extends ArrayAdapter<CurrencyItem> {

    public CurrencyAdapter(Context context, ArrayList<CurrencyItem> currencyList) {
        super(context, 0, currencyList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.currency_spinner, parent, false
            );
        }

        ImageView imageViewCurrency = convertView.findViewById(R.id.imageView_euro);
        TextView textViewName = convertView.findViewById(R.id.textView_euro);

        CurrencyItem currentItem = getItem(position);

        if (currentItem != null) {
            imageViewCurrency.setImageResource(currentItem.getCurrencyImage());
            textViewName.setText(currentItem.getCurrencyName());
        }
        return convertView;
    }
}
