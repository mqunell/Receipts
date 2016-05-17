package com.mattqunell.receipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Login extends AppCompatActivity {
    EditText edittextUsername;
    EditText edittextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edittextUsername = (EditText) findViewById(R.id.login_edittext_username);
        edittextPassword = (EditText) findViewById(R.id.login_edittext_password);

        /*
        Button buttonLogin = (Button) findViewById(R.id.login_button_login);
        assert buttonLogin != null;
        buttonLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                // Until Create is functional, start Home by default
                startActivity(new Intent(Login.this, Home.class));
            }
        });
        */
    }


    public void logIn(View view) {
        String username = edittextUsername.getText().toString();
        String password = edittextPassword.getText().toString();

        try {
            String text;

            FileInputStream fileInputStream = openFileInput(username);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ((text = bufferedReader.readLine()) != null) {
                stringBuilder.append(text);
            }

            if (stringBuilder.toString().equals(password)) {
                Toast.makeText(getApplicationContext(), "Logging in", Toast.LENGTH_LONG).show();
                startActivity(new Intent(Login.this, Home.class));
            }
            else {
                Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_LONG).show();
            }
        }
        catch (FileNotFoundException e) {
            Toast.makeText(getApplicationContext(), "File with that username not found", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
