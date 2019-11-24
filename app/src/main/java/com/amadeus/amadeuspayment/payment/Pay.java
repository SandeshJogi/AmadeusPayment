package com.amadeus.amadeuspayment.payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.amadeus.amadeuspayment.MainActivity;
import com.amadeus.amadeuspayment.R;
import com.amadeus.amadeuspayment.confirmation.Confirmation;
import com.amadeus.amadeuspayment.processing.Processing;
import com.amadeus.amadeuspayment.spinner.CurrencyAdapter;
import com.amadeus.amadeuspayment.spinner.CurrencyItem;
import com.amadeus.amadeuspayment.spinner.ModeItem;
import com.amadeus.amadeuspayment.spinner.ModeAdapter;
import com.amadeus.amadeuspayment.spinner.ModeItem;
import com.amadeus.amadeuspayment.spinner.ModeItem;
import com.amadeus.amadeuspayment.splashScreen.HomeActivity;

import java.util.ArrayList;

public class Pay extends AppCompatActivity {


    public static final String EXTRA_PAY = "com.amadeus.amadeuspayment.EXTRA_PAY";
    public static final String EXTRA_CURPAY = "com.amadeus.amadeuspayment.EXTRA_CURPAY";
    public static final String EXTRA_WALBAL = "com.amadeus.amadeuspayment.EXTRA_WALBAL";

    private ArrayList<CurrencyItem> mCurrencyItems;
    private ArrayList<ModeItem> mModeItems;
    private CurrencyAdapter mAdapter;
    private ModeAdapter mMAdapter;

    private double walletBalance;
    private double amountToPay;
    private double euroBalance;
    private double inrBalance;
    private double poundBalance;
    private double yenBalance;
    private String clickedCurrencyName;

    private EditText editText_AmountToPay;

    private RadioGroup radioGroup_payments;

    private Button button_Pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        editText_AmountToPay = (EditText) findViewById(R.id.amount_pay);

        button_Pay = (Button) findViewById(R.id.button2);

        initList();

        Spinner spinnerCurrencies = findViewById(R.id.spinner_currencies);
        Spinner spinnerModes = findViewById(R.id.spinner_modes);

        mAdapter = new CurrencyAdapter(this, mCurrencyItems);
        spinnerCurrencies.setAdapter(mAdapter);

        mMAdapter = new ModeAdapter(this, mModeItems);
        spinnerModes.setAdapter(mMAdapter);

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

                openActivityProcessing();
            }
        });

    }

    public void openActivityProcessing(){
        Intent intent = new Intent(this, Processing.class);
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
        mCurrencyItems.add(new CurrencyItem("USD", R.drawable.dollar));

        mModeItems = new ArrayList<>();
        mModeItems.add(new ModeItem( "TapPay", R.drawable.nfc));
        mModeItems.add(new ModeItem("QRCode", R.drawable.qrcode));
        mModeItems.add(new ModeItem("  GPay", R.drawable.ic_google_pay));
        mModeItems.add(new ModeItem("  APay", R.drawable.ic_apple_pay));
        mModeItems.add(new ModeItem(" CreditCard", R.drawable.visa));
        mModeItems.add(new ModeItem(" NetBanking", R.drawable.netbanking));
    }

   /* public void onPaymentSelected(View view){
        radioGroup_payments = (RadioGroup) findViewById(R.id.radioGroup);
        switch (radioGroup_payments.getCheckedRadioButtonId()){
            case R.id.apay:
                Toast.makeText(this, "ApplePay", Toast.LENGTH_SHORT).show();
                break;
            case R.id.gpay:
                Toast.makeText(this, "GooglePay", Toast.LENGTH_SHORT).show();
                break;
            case R.id.paypal:
                Toast.makeText(this, "PayPal", Toast.LENGTH_SHORT).show();
                break;
        }
    }*/
}
