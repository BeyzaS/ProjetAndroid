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

        //TO ADD THE NAVIGATION DRAWER TO THE ACTIVITY
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_alltowns);
        navigationView.setNavigationItemSelectedListener(this);

        //GET ALL TOWNS FROM DATABASE
        List<Location> allTowns = HouseSellingDatabase.getInstance(this).locationDAO().getAllTowns();

        //PUT ALL TOWNS IN THE LISTVIEW
        ListView listView = (ListView)findViewById(R.id.list);
        ArrayAdapter<Location> adapter = new ArrayAdapter<Location>(this,android.R.layout.simple_list_item_1, allTowns);
        listView.setAdapter(adapter);

        //WE NEED TO GET THE ID OF THE HOUSE SELECTED
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Location location = (Location) parent.getAdapter().getItem(position);

                //DEFINIE THE NEXT ACTIVITY
                Intent intent = new Intent(AllTowns.this, DetailsLocation.class);

                //PASS NECESSARY VALUES TO THE NEXT ACTIVITY
                intent.putExtra("IdLocation", location.getId());
                intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

                //START THE ACTIVITY
                startActivity(intent);
            }
        });
    }

    //TO REDIRECT MENUS TO THE RIGHT PAGE THANKS TO THE ID OF THE ITEM OF THE NAVIGATION DRAWER
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

    //ACTION TO THE BACK BUTTON
    public void onBackPressed(){
        Intent intent = new Intent(this, CreateSale.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
        //IF AN ACTIVITY IS PAUSED OR STOPPED, THE SYSTEM CAN DROP THE ACTIVITY FROM MEMORY BY
        //EITHER ASKING IT TO FINISH, OR SIMPLY KILLING ITS PROCESS. WHEN IT IS DISPLAYED AGAIN
        //TO THE USER, IT MUST BE COMPLETELY RESTARTED AND RESTORED TO ITS PREVIOUS STATE.
        finish();
    }
}