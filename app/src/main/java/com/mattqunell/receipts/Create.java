package com.mattqunell.receipts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;

public class Create extends AppCompatActivity {
    EditText edittextUsername;
    EditText edittextPasswordInput;
    EditText edittextPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        edittextUsername = (EditText) findViewById(R.id.create_edittext_username);
        edittextPasswordInput = (EditText) findViewById(R.id.create_edittext_passwordInput);
        edittextPasswordConfirm = (EditText) findViewById(R.id.create_edittext_passwordConfirm);
    }


    // create_button_submit
    public void createButtonSubmit(View v) {
        String username = edittextUsername.getText().toString();
        String password = edittextPasswordInput.getText().toString();
        String passwordConfirm = edittextPasswordConfirm.getText().toString();

        // Verify that the passwords are the same
        if (password.equals(passwordConfirm)) {
            try {
                FileOutputStream fileOutputStream = openFileOutput(username, MODE_PRIVATE);
                fileOutputStream.write(password.getBytes());
                fileOutputStream.close();

                Toast.makeText(getApplicationContext(), "Account created; logging in", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Create.this, Home.class));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }


    // create_button_clear
    public void createButtonClear(View v) {
        // Clear the EditTexts
        edittextUsername.setText("");
        edittextPasswordInput.setText("");
        edittextPasswordConfirm.setText("");

        // Give focus to edittextUsername
        edittextUsername.requestFocus();
    }
}