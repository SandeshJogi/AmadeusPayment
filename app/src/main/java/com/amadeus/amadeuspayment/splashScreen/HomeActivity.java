package com.amadeus.amadeuspayment.splashScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amadeus.amadeuspayment.R;
import com.amadeus.amadeuspayment.confirmation.Confirmation;
import com.amadeus.amadeuspayment.payment.Pay;

public class HomeActivity extends AppCompatActivity {

    public static final String EXTRA_EURO = "com.amadeus.amadeuspayment.EXTRA_EURO";
    public static final String EXTRA_INR = "com.amadeus.amadeuspayment.EXTRA_INR";
    public static final String EXTRA_POUND = "com.amadeus.amadeuspayment.EXTRA_POUND";
    public static final String EXTRA_YEN = "com.amadeus.amadeuspayment.EXTRA_YEN";

    private double wallet_balance;
    private double euroBalance;
    private double inrBalance;
    private double poundBalance;
    private double yenBalance;

    private EditText editText_WalletBal;

    private TextView euroAmount;
    private TextView inrAmount;
    private TextView poundAmount;
    private TextView yenAmount;

    private Button button_Scan;
    private Button button_Convert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        editText_WalletBal = (EditText) findViewById(R.id.editText2);

        euroAmount = (TextView) findViewById(R.id.euro_value);
        inrAmount = (TextView) findViewById(R.id.inr_value);
        poundAmount = (TextView) findViewById(R.id.pound_value);
        yenAmount = (TextView) findViewById(R.id.yen_value);

        button_Scan = (Button) findViewById(R.id.button);
        button_Convert = (Button) findViewById(R.id.currency_convert);

        Intent intent = getIntent();

        if (intent != null){
            editText_WalletBal.setText(String.format("%.5f",intent.getDoubleExtra(Confirmation.EXTRA_USDAMT, 0)));
            button_Convert.performClick();
        }

        button_Convert.performClick();
        // MAIN LOGIC OF CURRENCY CONVERSION
        button_Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallet_balance = Double.valueOf(editText_WalletBal.getText().toString());

                euroBalance = wallet_balance*0.90;
                euroAmount.setText(String.format("%.4f", euroBalance));

                inrBalance = wallet_balance*70.52;
                inrAmount.setText(String.format("%.4f", inrBalance));

                poundBalance = wallet_balance*0.77;
                poundAmount.setText(String.format("%.4f", poundBalance));

                yenBalance = wallet_balance*108.18;
                yenAmount.setText(String.format("%.4f", yenBalance));
            }
        });

        button_Scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wallet_balance = Double.valueOf(editText_WalletBal.getText().toString());

                openActivityPay();
            }
        });
    }

    public void openActivityPay(){
        Intent intent = new Intent(this, Pay.class);
        intent.putExtra(EXTRA_EURO, euroBalance);
        intent.putExtra(EXTRA_INR, inrBalance);
        intent.putExtra(EXTRA_POUND, poundBalance);
        intent.putExtra(EXTRA_YEN, yenBalance);

        startActivity(intent);
    }
}
