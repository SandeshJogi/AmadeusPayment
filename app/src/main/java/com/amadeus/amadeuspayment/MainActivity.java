package com.amadeus.amadeuspayment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;

import com.amadeus.amadeuspayment.cmt.Main2Activity;
import com.amadeus.amadeuspayment.splashScreen.HomeActivity;

public class MainActivity extends AppCompatActivity {

    // This command is used to create a SPLASH Screen or a LOADING Screen
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // code to display the Splash or the initial loading screen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.i("MainActivity", "Reached main activity");
                Intent homeIntent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
