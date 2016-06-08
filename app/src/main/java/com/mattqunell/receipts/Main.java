package com.mattqunell.receipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main extends AppCompatActivity {
    public static final String FILENAME_NEW = "new_receipts.txt";
    public static final String FILENAME_ARCHIVED = "archived_receipts.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    // main_button_create
    public void mainButtonCreate(View v) {
        startActivity(new Intent(Main.this, CreateAccount.class));
    }


    // main_button_login
    public void mainButtonLogin(View v) {
        startActivity(new Intent(Main.this, LoginAccount.class));
    }


    // main_button_skip
    public void mainButtonSkip(View v) {
        startActivity(new Intent(Main.this, Home.class));
    }
}
