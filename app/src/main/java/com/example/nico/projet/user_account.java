package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.User;

import java.util.List;

public class user_account extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        //ADD THE NAVIGATION DRAWER
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_useraccount);
        navigationView.setNavigationItemSelectedListener(this);

        //GET THE RIGHT USER
        User user = HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0));

        //GET THE VALUES OF THE USER AND PUT THEM IT INTO THE EDITTEXTS
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

    //ACTION TO THE BUTTON SAVE
    public void saveuser(View view) {
        //GET THE RIGHT USER THANKS TO THE IDUSER
        User user = HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0));

        //SET THE VALUES OF THE USER
        user.setUsername(((EditText)findViewById(R.id.username_account)).getText().toString());
        user.setLastname(((EditText)findViewById(R.id.lastname_account)).getText().toString());
        user.setFirstname(((EditText)findViewById(R.id.firstname_account)).getText().toString());
        user.setEmail(((EditText)findViewById(R.id.email_account)).getText().toString());
        user.setPassword(((EditText)findViewById(R.id.password_account)).getText().toString());

        //UPDATE DATABASE
        HouseSellingDatabase.getInstance(this).userDAO().updateUser(user);

        //GO TO THE PREVIOUS ACTIVITY WHICH IS THE LIST OF HOUSES OF THE SELLER
        Intent intent = new Intent(this, ListOfActivity.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
    }

    public void deleteuser(View view) {
        //WE JUST NEED THE USERID LATER ...
        User user = HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0));

        //WE NEED THIS FOR THE SIZE OF THE LIST
        List <House> houses = HouseSellingDatabase.getInstance(this).houseDAO().getHousesOfUserForDelete(user.getId());

        //DELETE THE HOUSES OF THE USER
        HouseSellingDatabase.getInstance(this).houseDAO().deleteHouses(houses);

        //DELETE USER
        HouseSellingDatabase.getInstance(this).userDAO().deleteUser(HouseSellingDatabase.getInstance(this).userDAO().getUserById(getIntent().getIntExtra("IdUser", 0)));

        //GO TO THE MAINACTIVITY BECAUSE
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    //TO REDIRECT MENUS TO THE RIGHT PAGE THANKS TO THE ID OF THE ITEM OF THE NAVIGATION DRAWER
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                Intent intent1 = new Intent(user_account.this, Languages.class);
                startActivity(intent1);
                return true;

            case R.id.item_about:
                Intent intent2 = new Intent(user_account.this, About.class);
                startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(user_account.this, SignIn.class);
                startActivity(intent3);
                return true;

            default:
                return false;
        }
    }

    //ACTION THE BACK BUTTON
    public void onBackPressed() {
        Intent intent = new Intent(this, ListOfActivity.class);
        intent.putExtra("IdUser", getIntent().getIntExtra("IdUser", 0));
        startActivity(intent);
        finish();
    }
}