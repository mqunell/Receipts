package com.mattqunell.receipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // main_button_create
    public void mainButtonCreate(View v) {
        startActivity(new Intent(Main.this, Create.class));
    }


    // main_button_login
    public void mainButtonLogin(View v) {
        startActivity(new Intent(Main.this, Login.class));
    }


    // main_button_skip
    public void mainButtonSkip(View v) {
        startActivity(new Intent(Main.this, Home.class));
    }
}
