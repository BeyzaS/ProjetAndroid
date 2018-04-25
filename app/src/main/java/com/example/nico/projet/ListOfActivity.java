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
import com.example.nico.projet.Model.House;

import java.util.List;

public class ListOfActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_listofactivity);
        navigationView.setNavigationItemSelectedListener(this);

        List<House> allHouses = HouseSellingDatabase.getInstance(this).houseDAO().getHousesOfUserForDelete(getIntent().getIntExtra("IdUser", 0));;

        ListView listView = (ListView)findViewById(R.id.listview_houses);
        ArrayAdapter<House> adapter = new ArrayAdapter<House>(this,android.R.layout.simple_list_item_1, allHouses);
        listView.setAdapter(adapter);

        // WE NEED TO GET THE ID OF THE HOUSE SELECTED
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //House commercial = (House) parent.getAdapter().getItem(position);
                final House house = (House)parent.getAdapter().getItem(position);


                Intent intent = new Intent(ListOfActivity.this, DetailsHouse.class);

                intent.putExtra("IdHouse", house.getId());
                intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

                startActivity(intent);
            }
        });

    }

    public void create_sale(View view){
        Intent intent = new Intent(this, CreateSale.class);

        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);

    }

    public void useraccount_update(View view){
        Intent intent = new Intent(this, user_account.class);

        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                //Intent intent1 = new Intent(ListOfActivity.this, Languages.class);
                //startActivity(intent1);
                return true;

            case R.id.item_about:
                //Intent intent2 = new Intent(ListOfActivity.this, About.class);
                //startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(ListOfActivity.this, SignIn.class);
                startActivity(intent3);
                return true;


            default:
                return false;
        }
    }
}