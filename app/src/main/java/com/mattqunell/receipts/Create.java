package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);


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
    }
}