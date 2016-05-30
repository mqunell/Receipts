package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class New extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        // ListView adaptor
        ArrayList<String[]> r = CsvManager.readCsvFile(getFilesDir().toString());
        ArrayList<String> receipts = new ArrayList<>();

        for (String[] s : r) {
            receipts.add(Arrays.toString(s));
        }

        ListView listviewNewReceipts = (ListView) findViewById(R.id.new_listview_newReceipts);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, receipts);
        listviewNewReceipts.setAdapter(adapter);
    }
}
