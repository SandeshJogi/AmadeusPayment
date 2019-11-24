package com.amadeus.amadeuspayment.cmt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.amadeus.amadeuspayment.R;
import com.amadeus.amadeuspayment.splashScreen.HomeActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Main2Activity", "Reached here");
                openHomeActivity();
            }
        });
    }

    private void openHomeActivity() {
        Intent intent = new Intent(Main2Activity.this, HomeActivity.class);
        startActivity(intent);
    }
}
