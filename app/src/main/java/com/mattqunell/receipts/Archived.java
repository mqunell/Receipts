package com.mattqunell.receipts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Archived extends AppCompatActivity {
    private String dir;
    private String filenameArchived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archived);

        dir = getFilesDir().toString();
        filenameArchived = "archived_receipts.csv";

        // ListView adaptor
        ArrayList<String[]> r = CsvManager.readCsvFile(dir, filenameArchived);
        ArrayList<String> receipts = new ArrayList<>();

        for (String[] s : r) {
            receipts.add(Arrays.toString(s));
        }

        ListView listviewArchivedReceipts = (ListView) findViewById(R.id.archived_listview_archivedReceipts);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, receipts);
        listviewArchivedReceipts.setAdapter(adapter);
    }
}
