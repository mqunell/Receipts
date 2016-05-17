package com.mattqunell.receipts;

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

        /*
        // Declare the EditTexts for the button handlers
        final EditText edittextUsername = (EditText) findViewById(R.id.create_edittext_username);
        assert edittextUsername != null;

        final EditText edittextPasswordInput = (EditText) findViewById(R.id.create_edittext_passwordInput);
        assert edittextPasswordInput != null;

        final EditText edittextPasswordConfirm = (EditText) findViewById(R.id.create_edittext_passwordConfirm);
        assert edittextPasswordConfirm != null;


        // buttonSubmit
        Button buttonSubmit = (Button) findViewById(R.id.create_button_submit);
        assert buttonSubmit != null;
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Verify that the passwords are the same
                if (edittextPasswordInput.getText().toString().equals(edittextPasswordConfirm.getText().toString())) {
                    // Create the account - NOT YET IMPLEMENTED, currently only a Toast
                    Toast.makeText(getApplicationContext(), "Passwords match", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // buttonClear
        Button buttonClear = (Button) findViewById(R.id.create_button_clear);
        assert buttonClear != null;
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Set the EditTexts to ""
                edittextUsername.setText("");
                edittextPasswordInput.setText("");
                edittextPasswordConfirm.setText("");

                // Give focus to create_edittext_username
                edittextUsername.requestFocus();
            }
        });
        */
    }


    /*
     *TEST: Use XML onClick attributes instead of Java setOnClickListeners
     */

    public void buttonSubmit(View v) {
        String username = edittextUsername.getText().toString();
        String password = edittextPasswordInput.getText().toString();
        String passwordConfirm = edittextPasswordConfirm.getText().toString();

        // Verify that the passwords are the same
        if (password.equals(passwordConfirm)) {
            try {
                FileOutputStream fileOutputStream = openFileOutput(username, MODE_PRIVATE);
                fileOutputStream.write(password.getBytes());
                fileOutputStream.close();
                Toast.makeText(getApplicationContext(), "Account created", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
        }
    }


    public void buttonClear(View v) {
        // Clear the EditTexts
        edittextUsername.setText("");
        edittextPasswordInput.setText("");
        edittextPasswordConfirm.setText("");

        // Give focus to edittextUsername
        edittextUsername.requestFocus();
    }
}