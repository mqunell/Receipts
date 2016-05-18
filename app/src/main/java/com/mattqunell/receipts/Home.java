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


    public void addReceipt(View v) {
        //startActivity(new Intent(Home.this, Add.class));
    }


    public void newReceipts(View v) {
        //startActivity(new Intent(Home.this, New.class));
    }


    public void oldReceipts(View v) {
        //startActivity(new Intent(Home.this, Old.class));
    }
}
