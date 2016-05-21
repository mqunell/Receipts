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

        Button buttonCreate = (Button) findViewById(R.id.main_button_create);
        assert buttonCreate != null;
        buttonCreate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Create.class));
            }
        });

        Button buttonLogin = (Button) findViewById(R.id.main_button_login);
        assert buttonLogin != null;
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Login.class));
            }
        });

        Button buttonSkip = (Button) findViewById(R.id.main_button_skip);
        assert buttonSkip != null;
        buttonSkip.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(Main.this, Home.class));
            }
        });
    }
}
