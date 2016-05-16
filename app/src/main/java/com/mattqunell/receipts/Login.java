package com.mattqunell.receipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button buttonLogin = (Button) findViewById(R.id.login_button_login);
        assert buttonLogin != null;
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Until Create is functional, start Home by default
                startActivity(new Intent(Login.this, Home.class));
            }
        });
    }
}
