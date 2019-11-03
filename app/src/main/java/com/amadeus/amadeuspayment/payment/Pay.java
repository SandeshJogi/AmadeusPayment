package com.amadeus.amadeuspayment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.amadeus.amadeuspayment.MainActivity;
import com.amadeus.amadeuspayment.R;
import com.amadeus.amadeuspayment.confirmation.Confirmation;
import com.amadeus.amadeuspayment.spinner.CurrencyAdapter;
import com.amadeus.amadeuspayment.spinner.CurrencyItem;
import com.amadeus.amadeuspayment.splashScreen.HomeActivity;

import java.util.ArrayList;

public class Pay extends AppCompatActivity {


    public static final String EXTRA_PAY = "com.amadeus.amadeuspayment.EXTRA_PAY";
    public static final String EXTRA_CURPAY = "com.amadeus.amadeuspayment.EXTRA_CURPAY";
    public static final String EXTRA_WALBAL = "com.amadeus.amadeuspayment.EXTRA_WALBAL";

    private ArrayList<CurrencyItem> mCurrencyItems;
    private CurrencyAdapter mAdapter;

    private double walletBalance;
    private double amountToPay;
    private double euroBalance;
    private double inrBalance;
    private double poundBalance;
    private double yenBalance;
    private String clickedCurrencyName;

    private EditText editText_AmountToPay;

    private Button button_Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        editText_AmountToPay = (EditText) findViewById(R.id.amount_pay);

        button_Pay = (Button) findViewById(R.id.button2);

        initList();

        Spinner spinnerCurrencies = findViewById(R.id.spinner_currencies);

        mAdapter = new CurrencyAdapter(this, mCurrencyItems);
        spinnerCurrencies.setAdapter(mAdapter);

        spinnerCurrencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                CurrencyItem clickedItem = (CurrencyItem) adapterView.getItemAtPosition(i);
                clickedCurrencyName = clickedItem.getCurrencyName();
                Toast.makeText(Pay.this, clickedCurrencyName + " selected", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Intent intent = getIntent();
        euroBalance = intent.getDoubleExtra(HomeActivity.EXTRA_EURO, 0);
        inrBalance = intent.getDoubleExtra(HomeActivity.EXTRA_INR, 0);
        poundBalance = intent.getDoubleExtra(HomeActivity.EXTRA_POUND, 0);
        yenBalance = intent.getDoubleExtra(HomeActivity.EXTRA_YEN, 0);

        // Calculating Wallet Balance
        walletBalance = euroBalance/0.90;

        button_Pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amountToPay = Double.valueOf(editText_AmountToPay.getText().toString());

                openActivityConfirmation();
            }
        });

    }

    public void openActivityConfirmation(){
        Intent intent = new Intent(this, Confirmation.class);
        intent.putExtra(EXTRA_WALBAL, walletBalance);
        intent.putExtra(EXTRA_PAY, amountToPay);
        intent.putExtra(EXTRA_CURPAY , clickedCurrencyName);
        startActivity(intent);
    }

    private void initList(){
        mCurrencyItems = new ArrayList<>();
        mCurrencyItems.add(new CurrencyItem("Euro", R.drawable.euro));
        mCurrencyItems.add(new CurrencyItem("INR", R.drawable.inr));
        mCurrencyItems.add(new CurrencyItem("Pound", R.drawable.pound));
        mCurrencyItems.add(new CurrencyItem("Yen", R.drawable.yen));
    }
}
