package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ArchivedReceipts extends AppCompatActivity {
    private String dir;
    private String filenameArchived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived_receipts);

        dir = getFilesDir().toString();
        filenameArchived = "archived_receipts.txt";

        // ListView adaptor
        ArrayList<String> receipts = FileManager.readFile(dir, filenameArchived);

        ListView listviewArchivedReceipts = (ListView) findViewById(R.id.archived_listview_archivedReceipts);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, receipts);
        listviewArchivedReceipts.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_archived, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.archived_action_sort) {
            // Sort the receipts

        }
        if (id == R.id.archived_action_clear) {
            // Rename the receipt file to archived_receipts
            FileManager.clearReceipts(dir, filenameArchived);
        }

        return super.onOptionsItemSelected(item);
    }
}
