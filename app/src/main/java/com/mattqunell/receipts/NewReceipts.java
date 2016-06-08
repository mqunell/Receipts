package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NewReceipts extends AppCompatActivity {
    private String dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_receipts);

        dir = getFilesDir().toString();

        // ListView adaptor
        ArrayList<String> receipts = FileManager.readFile(dir, Main.FILENAME_NEW);

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
        // Handle action bar item clicks
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.new_action_archive) {
            // Rename the receipt file to archived_receipts
            FileManager.archiveReceipts(dir);
        }
        else if (id == R.id.new_action_clear) {
            // Rename the receipt file to archived_receipts
            FileManager.clearReceipts(dir, Main.FILENAME_NEW);
        }

        return super.onOptionsItemSelected(item);
    }
}
