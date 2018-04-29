package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;

public class CreateSale extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sale);

        //TO ADD THE NAVIGATION DRAWER TO THE ACTIVITY
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_createsale);
        navigationView.setNavigationItemSelectedListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.type_create);

        //CREATE AN ARRAYADAPTER USING THE STRING ARRAY AND A DEFAULT SPINNER LAYOUT
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.type_array, android.R.layout.simple_spinner_item);
        //SPECIFY THE LAYOUT TO USE WHEN THE LIST OF CHOICES APPEARS
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //APPLY THE ADAPTER TO THE SPINNER
        spinner.setAdapter(adapter);

        //AUTOCOMPLETE TOWN LIST
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.town_autocomplete);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, HouseSellingDatabase.getInstance(this).locationDAO().getAllTown());
        textView.setAdapter(adapter2);
    }

    public void create_house(View view) {
        EditText edescription = (EditText)findViewById(R.id.description_create);
        String description = edescription.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.type_create);
        String type = spinner.getSelectedItem().toString();

        EditText em2 = (EditText)findViewById(R.id.m2_create);
        int m2 = Integer.parseInt(em2.getText().toString());

        EditText eprice = (EditText)findViewById(R.id.price_create);
        int price = Integer.parseInt(eprice.getText().toString());

        EditText eaddress = (EditText)findViewById(R.id.address_create);
        String address = eaddress.getText().toString();

        AutoCompleteTextView etown = (AutoCompleteTextView)findViewById(R.id.town_autocomplete);
        String town = etown.getText().toString();

        if(validate(description, m2, price, address, town)) {
            int idTown = HouseSellingDatabase.getInstance(this).locationDAO().getLocationId(town);

            HouseSellingDatabase.getInstance(this).houseDAO().insertHouse(new House(type, description, m2, price, address, idTown, getIntent().getIntExtra("IdUser", 0)));

            Intent intent = new Intent(this, ListOfActivity.class);
            intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
            startActivity(intent);
        }
    }

    public Boolean validate(String description, int m2, int price, String address, String town) {
        if(description.isEmpty() || Integer.valueOf(m2).toString().isEmpty() || Integer.valueOf(price).toString().isEmpty() || address.isEmpty() || town.isEmpty() ){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!HouseSellingDatabase.getInstance(this).locationDAO().getAllTown().contains(town)) {
            Toast.makeText(this,"Please add the town before create the house", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    //ACTION TO THE BUTTON "NEW TOWN"
    public void create_location(View view) {
        Intent intent = new Intent(this, create_location.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    //ACTION TO THE BUTTON "ALL TOWNS"
    public void consult_all_town(View view) {
        Intent intent = new Intent(this, AllTowns.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    @Override
    //TO REDIRECT MENUS TO THE RIGHT PAGE THANKS TO THE ID OF THE ITEM OF THE NAVIGATION DRAWER
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                Intent intent1 = new Intent(CreateSale.this, Languages.class);
                startActivity(intent1);
                return true;
            case R.id.item_about:
                Intent intent2 = new Intent(CreateSale.this, About.class);
                startActivity(intent2);
                return true;
            case R.id.item_logout:
                Intent intent3 = new Intent(CreateSale.this, SignIn.class);
                startActivity(intent3);
                return true;
            default:
                return false;
        }
    }

    //ACTION TO THE BACK BUTTON
    public void onBackPressed() {
        Intent intent = new Intent(this, ListOfActivity.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
        finish();
    }
}