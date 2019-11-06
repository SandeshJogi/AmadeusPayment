package com.amadeus.amadeuspayment.processing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.amadeus.amadeuspayment.R;
import com.amadeus.amadeuspayment.confirmation.Confirmation;
import com.amadeus.amadeuspayment.payment.Pay;

import java.util.Timer;
import java.util.TimerTask;

public class Processing extends AppCompatActivity {

    public static final String EXTRA_PPAY = "com.amadeus.amadeuspayment.EXTRA_PPAY";
    public static final String EXTRA_PCURPAY = "com.amadeus.amadeuspayment.EXTRA_PCURPAY";
    public static final String EXTRA_PWALBAL = "com.amadeus.amadeuspayment.EXTRA_PWALBAL";

    private double walletBalance;
    private double amt2pay;
    private String curName;

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);

        Intent intent = getIntent();
        amt2pay = intent.getDoubleExtra(Pay.EXTRA_PAY, 0);
        curName = intent.getStringExtra(Pay.EXTRA_CURPAY);
        walletBalance = intent.getDoubleExtra(Pay.EXTRA_WALBAL, 0);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                openActivityConfirmation();
                finish();
            }
        },2000);
    }

    public void openActivityConfirmation(){
        Intent intent = new Intent(this, Confirmation.class);
        intent.putExtra(EXTRA_PWALBAL, walletBalance);
        intent.putExtra(EXTRA_PPAY, amt2pay);
        intent.putExtra(EXTRA_PCURPAY , curName);
        startActivity(intent);
    }
}
