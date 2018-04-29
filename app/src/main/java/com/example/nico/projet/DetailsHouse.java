package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DetailsHouse extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_house);

        //TO ADD THE NAVIGATION DRAWER TO THE ACTIVITY
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_detailshouse);
        navigationView.setNavigationItemSelectedListener(this);

        //GET THE HOUSE SELECTED IN THE PREVIOUS ACTIVITY
        House house = HouseSellingDatabase.getInstance(this).houseDAO().getHouseById(getIntent().getIntExtra("IdHouse", 0));

        //PUT THE VALUES OF THIS HOUSE INTO THE EDITTEXTS
        EditText edescription = (EditText)findViewById(R.id.description_details_house);
        edescription.setText(house.getDescription(), TextView.BufferType.EDITABLE);

        EditText em2 = (EditText)findViewById(R.id.m2_details_house);
        em2.setText(String.valueOf(house.getSquareMeter()), TextView.BufferType.EDITABLE);

        EditText eprice = (EditText)findViewById(R.id.price_details_house);
        eprice.setText(String.valueOf(house.getPrice()), TextView.BufferType.EDITABLE);

        EditText eaddress = (EditText)findViewById(R.id.address_details_house);
        eaddress.setText(house.getAddress(), TextView.BufferType.EDITABLE);
    }

    //ACTION TO THE BUTTON "SAVE"
    public void savehouse(View view) {
        //GET THE HOUSE SELECTED IN THE PREVIOUS ACTIVITY
        House house = HouseSellingDatabase.getInstance(this).houseDAO().getHouseById(getIntent().getIntExtra("IdHouse", 0));

        //SET THE VALUES FROM THE EDITTEXT
        house.setDescription(((EditText)findViewById(R.id.description_details_house)).getText().toString());
        house.setSquareMeter(Integer.parseInt(((EditText)findViewById(R.id.m2_details_house)).getText().toString()));
        house.setPrice(Integer.parseInt(((EditText)findViewById(R.id.price_details_house)).getText().toString()));
        house.setAddress(((EditText)findViewById(R.id.address_details_house)).getText().toString());

        //UPDATE DATABASE WITH THE NEW VALUES
        HouseSellingDatabase.getInstance(this).houseDAO().updateHouse(house);

        //WHEN UPDATING DATABASE IS FINISHED, GO TO THE PREVIOUS ACTIVITY
        //IN THIS WAY, THE SELLER CAN SEE THAT THE HOUSE WHICH VALUES ARE CHANGED IS UPDATE IN THE ACTIVITY WHICH SHOW ALL HIS HOUSES
        Intent intent = new Intent(this, ListOfActivity.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    //ACTION TO THE BUTTON "DELETE"
    public void deletehouse(View view) {

        //GET THE HOUSE SELECTED AT THE PREVIOUS ACTIVITY
        House house = HouseSellingDatabase.getInstance(this).houseDAO().getHouseById(getIntent().getIntExtra("IdHouse", 0));

        //DELETE HOUSE FROM DATABASE
        HouseSellingDatabase.getInstance(this).houseDAO().deleteHouse(house);

        Intent intent = new Intent(this, ListOfActivity.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    @Override
    //TO REDIRECT MENUS TO THE RIGHT PAGE THANKS TO THE ID OF THE ITEM OF THE NAVIGATION DRAWER
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                Intent intent1 = new Intent(DetailsHouse.this, Languages.class);
                startActivity(intent1);
                return true;

            case R.id.item_about:
                Intent intent2 = new Intent(DetailsHouse.this, About.class);
                startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(DetailsHouse.this, SignIn.class);
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