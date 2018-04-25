package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.Location;

public class DetailsLocation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_location);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_detailslocation);
        navigationView.setNavigationItemSelectedListener(this);

        final Location location = HouseSellingDatabase.getInstance(this).locationDAO().getLocationById(getIntent().getIntExtra("IdLocation", 0));

        EditText edescription = (EditText) findViewById(R.id.description_details_location);
        edescription.setText(location.getDescription(), TextView.BufferType.EDITABLE);

        EditText etown = (EditText) findViewById(R.id.town_details_location);
        etown.setText(location.getTown(), TextView.BufferType.EDITABLE);


        CheckBox cbswimmingpool = (CheckBox)findViewById(R.id.hasswimmingpool_details_location);
        cbswimmingpool.setChecked(location.isHasSwimmingPool());


        CheckBox cbcinema = (CheckBox)findViewById(R.id.hascinema_details_location);
        cbcinema.setChecked(location.isHasCinema());


        CheckBox cbsportcenter = (CheckBox)findViewById(R.id.hassportcenter_details_location);
        cbsportcenter.setChecked(location.isHasSportCenter());


    }

    public void savelocation(View view) {

        Location location = HouseSellingDatabase.getInstance(this).locationDAO().getLocationById(getIntent().getIntExtra("IdLocation", 0));

        location.setDescription(((EditText) findViewById(R.id.description_details_location)).getText().toString());
        location.setTown(((EditText) findViewById(R.id.town_details_location)).getText().toString());
        location.setHasSwimmingPool(onCheckBoxClickedSwimmingPool2(view));
        location.setHasCinema(onCheckBoxClickedCinema2(view));
        location.setHasSportCenter(onCheckBoxClickedSportCenter2(view));


        HouseSellingDatabase.getInstance(this).locationDAO().updateLocation(location);

        Intent intent = new Intent(this, AllTowns.class);

        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

        startActivity(intent);
    }

    public void deletelocation(View view) {

        Location location = HouseSellingDatabase.getInstance(this).locationDAO().getLocationById(getIntent().getIntExtra("IdLocation", 0));

        HouseSellingDatabase.getInstance(this).locationDAO().deleteLocation(location);

        Intent intent = new Intent(this, AllTowns.class);

        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

        startActivity(intent);
    }

    public boolean onCheckBoxClickedSwimmingPool2(View view){
        CheckBox cbSwimmingPool = (CheckBox) findViewById(R.id.hasswimmingpool_details_location);

        boolean swimmingpool_ischecked;
        if(cbSwimmingPool.isChecked()){
            swimmingpool_ischecked = true;
        }
        else{
            swimmingpool_ischecked = false;
        }
        return swimmingpool_ischecked;
    }

    public boolean onCheckBoxClickedCinema2(View view){
        CheckBox cbCinema = (CheckBox) findViewById(R.id.hascinema_details_location);

        boolean cinema_ischecked;
        if(cbCinema.isChecked()){
            cinema_ischecked = true;
        }
        else{
            cinema_ischecked = false;
        }
        return cinema_ischecked;
    }

    public boolean onCheckBoxClickedSportCenter2(View view){
        CheckBox cbSportCenter = (CheckBox) findViewById(R.id.hassportcenter_details_location);

        boolean sportcenter_ischecked;
        if(cbSportCenter.isChecked()){
            sportcenter_ischecked = true;
        }
        else{
            sportcenter_ischecked = false;
        }
        return sportcenter_ischecked;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                //Intent intent1 = new Intent(DetailsLocation.this, Languages.class);
                //startActivity(intent1);
                return true;

            case R.id.item_about:
                //Intent intent2 = new Intent(DetailsLocation.this, About.class);
                //startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(DetailsLocation.this, SignIn.class);
                startActivity(intent3);
                return true;


            default:
                return false;
        }
    }


}