package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.Location;

public class create_location extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_location);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_createlocation);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void insert_new_location(View view){

        EditText edescription = (EditText)findViewById(R.id.description_newlocation);
        String description = edescription.getText().toString();

        EditText etown = (EditText)findViewById(R.id.town_newlocation);
        String town = etown.getText().toString();

        if(validate(description, town)){
            HouseSellingDatabase.getInstance(this).locationDAO().insertLocation(new Location(description, town, onCheckBoxClickedSwimmingPool(view), onCheckBoxClickedCinema(view), onCheckBoxClickedSportCenter(view)));

            Intent intent = new Intent(this, CreateSale.class);

            intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

            startActivity(intent);
        }

    }

    public boolean onCheckBoxClickedSwimmingPool(View view){
        CheckBox cbSwimmingPool = (CheckBox) findViewById(R.id.hasswimmingpool_newlocation);

        boolean swimmingpool_ischecked;
        if(cbSwimmingPool.isChecked()){
            swimmingpool_ischecked = true;
        }
        else{
            swimmingpool_ischecked = false;
        }
        return swimmingpool_ischecked;
    }

    public boolean onCheckBoxClickedCinema(View view){
        CheckBox cbCinema = (CheckBox) findViewById(R.id.hascinema_newlocation);

        boolean cinema_ischecked;
        if(cbCinema.isChecked()){
            cinema_ischecked = true;
        }
        else{
            cinema_ischecked = false;
        }
        return cinema_ischecked;
    }

    public boolean onCheckBoxClickedSportCenter(View view){
        CheckBox cbSportCenter = (CheckBox) findViewById(R.id.hassportcenter_newlocation);

        boolean sportcenter_ischecked;
        if(cbSportCenter.isChecked()){
            sportcenter_ischecked = true;
        }
        else{
            sportcenter_ischecked = false;
        }
        return sportcenter_ischecked;
    }

    public Boolean validate(String description, String town)
    {
        boolean ok = false;
        if(description.isEmpty() || town.isEmpty() ){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
        }
        else{
            ok = true;
        }

        return ok;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                //Intent intent1 = new Intent(create_location.this, Languages.class);
                //startActivity(intent1);
                return true;

            case R.id.item_about:
                //Intent intent2 = new Intent(create_location.this, About.class);
                //startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(create_location.this, SignIn.class);
                startActivity(intent3);
                return true;


            default:
                return false;
        }
    }

}
