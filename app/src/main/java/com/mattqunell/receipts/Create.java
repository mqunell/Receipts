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


        // Declare the EditTexts for the button handlers
        final EditText edittextUsername = (EditText) findViewById(R.id.create_edittext_username);
        assert edittextUsername != null;

        final EditText edittextPasswordInput = (EditText) findViewById(R.id.create_edittext_passwordInput);
        assert edittextPasswordInput != null;

        final EditText edittextPasswordConfirm = (EditText) findViewById(R.id.create_edittext_passwordConfirm);
        assert edittextPasswordConfirm != null;


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