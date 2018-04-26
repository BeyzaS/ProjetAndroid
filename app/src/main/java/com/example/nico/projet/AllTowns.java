package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.Location;

import java.util.List;

public class AllTowns extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_towns);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_alltowns);
        navigationView.setNavigationItemSelectedListener(this);

        List<Location> allTowns = HouseSellingDatabase.getInstance(this).locationDAO().getAllTowns();


        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this,android.R.layout.simple_list_item_1, allTowns);
        listView.setAdapter(adapter);

        // WE NEED TO GET THE ID OF THE HOUSE SELECTED
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //House commercial = (House) parent.getAdapter().getItem(position);
                final Location location = (Location) parent.getAdapter().getItem(position);


                Intent intent = new Intent(AllTowns.this, DetailsLocation.class);

                intent.putExtra("IdLocation", location.getId());
                intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                Intent intent1 = new Intent(AllTowns.this, Languages.class);
                startActivity(intent1);
                return true;

            case R.id.item_about:
                Intent intent2 = new Intent(AllTowns.this, About.class);
                startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(AllTowns.this, SignIn.class);
                startActivity(intent3);
                return true;


            default:
                return false;
        }
    }
}