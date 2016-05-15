package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Create extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // create_button_clear
        Button buttonClear = (Button) findViewById(R.id.create_button_clear);
        buttonClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Set the EditTexts to ""
                EditText createEdittextUsername = (EditText) findViewById(R.id.create_edittext_username);
                createEdittextUsername.setText("");

                EditText createEdittextPasswordInput = (EditText) findViewById(R.id.create_edittext_passwordInput);
                createEdittextPasswordInput.setText("");

                EditText createEdittextPasswordConfirm = (EditText) findViewById(R.id.create_edittext_passwordConfirm);
                createEdittextPasswordConfirm.setText("");

                // Give focus to create_edittext_username
                createEdittextUsername.requestFocus();
            }
        });
    }
}