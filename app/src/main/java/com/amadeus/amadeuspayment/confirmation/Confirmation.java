package com.amadeus.amadeuspayment.confirmation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.amadeus.amadeuspayment.R;
import com.amadeus.amadeuspayment.payment.Pay;
import com.amadeus.amadeuspayment.splashScreen.HomeActivity;

public class Confirmation extends AppCompatActivity {

    public static final String EXTRA_USDAMT = "com.amadeus.amadeuspayment.EXTRA_USDAMT";

    private double walletBalance;
    private double amt2pay;
    private double usdamt;
    private String curName;
    private Button b2home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        b2home = (Button) findViewById(R.id.back2home);

        Intent intent = getIntent();
        amt2pay = intent.getDoubleExtra(Pay.EXTRA_PAY, 0);
        curName = intent.getStringExtra(Pay.EXTRA_CURPAY);
        walletBalance = intent.getDoubleExtra(Pay.EXTRA_WALBAL, 0);

        switch (curName){
            case "Euro" : usdamt = amt2pay/0.90;
            break;
            case "INR" : usdamt = amt2pay/70.52;
            break;
            case "Pound" : usdamt = amt2pay/0.77;
            break;
            case "Yen" : usdamt = amt2pay/108.18;
            break;
            default: usdamt = amt2pay;
        }

        walletBalance = walletBalance - usdamt;

        b2home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openActivityHome();

            }
        });
    }

    private void openActivityHome(){
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(EXTRA_USDAMT, walletBalance);
        startActivity(intent);
    }
}
