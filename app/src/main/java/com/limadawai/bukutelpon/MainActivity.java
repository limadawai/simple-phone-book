package com.limadawai.bukutelpon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView list;

//    ArrayList<KontakDataset> listdata;
    KontakAdapter kontakAdapter;
    DatabaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        list = (ListView) findViewById(R.id.lv);

        dbh = new DatabaseHelper(this);
        List<KontakDataset> listdata = new ArrayList<>();
        listdata = dbh.getAllKontak();
        kontakAdapter = new KontakAdapter(this, listdata);
        list.setAdapter(kontakAdapter);
        kontakAdapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.optionmenu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.tambah){
            Intent intent = new Intent(this, InsertUpdate.class);
            startActivity(intent);
        }
        return true;
    }
}