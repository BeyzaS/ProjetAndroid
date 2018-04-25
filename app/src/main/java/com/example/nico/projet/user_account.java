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
import android.widget.Toast;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.User;

import java.util.List;

public class user_account extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_useraccount);
        navigationView.setNavigationItemSelectedListener(this);

        User user = HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0));

        EditText eusername = (EditText)findViewById(R.id.username_account);
        eusername.setText(user.getUsername(), TextView.BufferType.EDITABLE);

        EditText elastname = (EditText)findViewById(R.id.lastname_account);
        elastname.setText(user.getLastname(), TextView.BufferType.EDITABLE);

        EditText efirstname = (EditText)findViewById(R.id.firstname_account);
        efirstname.setText(user.getFirstname(), TextView.BufferType.EDITABLE);

        EditText eemail = (EditText)findViewById(R.id.email_account);
        eemail.setText(user.getEmail(), TextView.BufferType.EDITABLE);

        EditText epassword = (EditText)findViewById(R.id.password_account);
        epassword.setText(user.getPassword(), TextView.BufferType.EDITABLE);

    }

    public void saveuser(View view)
    {
        User user = HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0));

        user.setUsername(((EditText)findViewById(R.id.username_account)).getText().toString());
        user.setLastname(((EditText)findViewById(R.id.lastname_account)).getText().toString());
        user.setFirstname(((EditText)findViewById(R.id.firstname_account)).getText().toString());
        user.setEmail(((EditText)findViewById(R.id.email_account)).getText().toString());
        user.setPassword(((EditText)findViewById(R.id.password_account)).getText().toString());


        // HouseSellingDatabase.getInstance(this).userDAO().updateUser(user.getId(), ((EditText)findViewById(R.id.firstname_account)).getText().toString(), ((EditText)findViewById(R.id.lastname_account)).getText().toString(), ((EditText)findViewById(R.id.username_account)).getText().toString(), ((EditText)findViewById(R.id.password_account)).getText().toString(),((EditText)findViewById(R.id.email_account)).getText().toString() );

        HouseSellingDatabase.getInstance(this).userDAO().updateUser(user);

        Intent intent = new Intent(this, ListOfActivity.class);

        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));

        startActivity(intent);

    }

    public void deleteuser(View view)
    {
        // WE JUST NEED THE USERID LATER ...
        User user = HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0));

        // WE NEED THIS FOR THE SIZE OF THE LIST
        List <House> houses = HouseSellingDatabase.getInstance(this).houseDAO().getHousesOfUserForDelete(user.getId());

        /*
        // WE DELETE ONE BY ONE THE HOUSE OF THE USER
        for (int i = 0 ; i <= houses.size() ; i++)
        {
            // FIRST DELETE HOUSES OF THE USER
            HouseSellingDatabase.getInstance(this).houseDAO().deleteHouse(HouseSellingDatabase.getInstance(this).houseDAO().getHousesOfUserForDelete(user.getId()).get(i));
        }
        */

        HouseSellingDatabase.getInstance(this).houseDAO().deleteHouses(houses);

        // DELETE USER
        HouseSellingDatabase.getInstance(this).userDAO().deleteUser(HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0)));

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                //Intent intent1 = new Intent(user_account.this, Languages.class);
                //startActivity(intent1);
                return true;

            case R.id.item_about:
                //Intent intent2 = new Intent(user_account.this, About.class);
                //startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(user_account.this, SignIn.class);
                startActivity(intent3);
                return true;


            default:
                return false;
        }
    }
}
