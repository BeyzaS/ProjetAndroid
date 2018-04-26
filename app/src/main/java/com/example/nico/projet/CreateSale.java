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
import com.example.nico.projet.Model.User;

public class CreateSale extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sale);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_createsale);
        navigationView.setNavigationItemSelectedListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.type_create);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.type_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        // autocomplete town list
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.town_autocomplete);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, HouseSellingDatabase.getInstance(this).locationDAO().getAllTown());
        textView.setAdapter(adapter2);
    }

    public void create_house(View view){
        EditText edescription = (EditText)findViewById(R.id.description_create);
        String description = edescription.getText().toString();

        Spinner spinner = (Spinner) findViewById(R.id.type_create);
        String type = spinner.getSelectedItem().toString();

        EditText em2 = (EditText)findViewById(R.id.m2_create);
        int m2 = Integer.parseInt(em2.getText().toString());

        EditText efloor = (EditText)findViewById(R.id.floor_create);
        int floor = Integer.parseInt(efloor.getText().toString());

        EditText eprice = (EditText)findViewById(R.id.price_create);
        int price = Integer.parseInt(eprice.getText().toString());

        EditText eaddress = (EditText)findViewById(R.id.address_create);
        String address = eaddress.getText().toString();

        AutoCompleteTextView etown = (AutoCompleteTextView)findViewById(R.id.town_autocomplete);
        String town = etown.getText().toString();

        if(validate(description, m2, floor, price, address, town)){
            int idTown = HouseSellingDatabase.getInstance(this).locationDAO().getLocationId(town);

            HouseSellingDatabase.getInstance(this).houseDAO().insertHouse(new House(type, description, m2, price, floor, address, idTown, getIntent().getIntExtra("IdUser", 0)));

            Intent intent = new Intent(this, ListOfActivity.class);
            intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
            startActivity(intent);
        }
    }

    public Boolean validate(String description, int m2, int floor, int price, String address, String town)
    {
        boolean ok = false;
        if(description.isEmpty() || Integer.valueOf(m2).toString().isEmpty() || Integer.valueOf(floor).toString().isEmpty() || Integer.valueOf(price).toString().isEmpty() || address.isEmpty() || town.isEmpty() ){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else{
            ok = true;
        }
        return ok;
    }

    public void create_location(View view)
    {
        Intent intent = new Intent(this, create_location.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    public void consult_all_town(View view)
    {
        Intent intent = new Intent(this, AllTowns.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item)
    {
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
}