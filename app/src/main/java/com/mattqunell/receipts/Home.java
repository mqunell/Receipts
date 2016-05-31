package com.mattqunell.receipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    // home_button_add
    public void homeButtonAdd(View v) {
        startActivity(new Intent(Home.this, Add.class));
    }


    // home_button_new
    public void homeButtonNew(View v) {
        startActivity(new Intent(Home.this, New.class));
    }


    // home_button_old
    public void homeButtonOld(View v) {
        startActivity(new Intent(Home.this, Archived.class));
    }
}
