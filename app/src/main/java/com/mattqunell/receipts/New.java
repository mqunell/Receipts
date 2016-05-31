package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class New extends AppCompatActivity {
    private String dir;
    private String filenameNew;
    private String filenameArchived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        dir = getFilesDir().toString();
        filenameNew = "receipts.csv";
        filenameArchived = "archived_receipts.csv";

        // ListView adaptor
        ArrayList<String[]> r = CsvManager.readCsvFile(dir, filenameNew);
        ArrayList<String> receipts = new ArrayList<>();

        for (String[] s : r) {
            receipts.add(Arrays.toString(s));
        }

        ListView listviewNewReceipts = (ListView) findViewById(R.id.new_listview_newReceipts);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, receipts);
        listviewNewReceipts.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_action_sort) {
            // Sort the receipts

        }
        if (id == R.id.new_action_archive) {
            // Rename the receipt file to archived_receipts
            CsvManager.archiveReceipts(dir);
        }

        return super.onOptionsItemSelected(item);
    }
}
