package com.example.nico.projet;

import android.content.Intent;
import android.os.Handler;
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
import com.example.nico.projet.Model.User;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //TO ADD THE NAVIGATION DRAWER TO THE ACTIVITY
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_register);
        navigationView.setNavigationItemSelectedListener(this);
    }

    //METHOD FOR THE ACTION OF THE BUTTON "REGISTER"
    public void register(View view) {

        //GET VALUES ENTERED IN THE EDITTEXTS AND CONVERT TO STRING
        EditText eusername = (EditText)findViewById(R.id.username_register);
        String username = eusername.getText().toString();

        EditText elastname = (EditText)findViewById(R.id.lastname_register);
        String lastname = elastname.getText().toString();

        EditText efirstname = (EditText)findViewById(R.id.firstname_register);
        String firstname = efirstname.getText().toString();

        EditText epassword = (EditText)findViewById(R.id.password_register);
        String password = epassword.getText().toString();

        EditText eemail = (EditText)findViewById(R.id.email_register);
        String email = eemail.getText().toString();

        //CHECK IF ALL FIELDS ARE FILLED IN
        if(validate(username, lastname, firstname, password, email)){
            //INSERT THE NEW USER INTO THE DATABASE
            User newUser = new User(firstname, lastname, username, password, email);
            HouseSellingDatabase.getInstance(this).userDAO().insertUser(newUser);

            //DEFINE THE NEXT ACTIVITY
            Intent intent = new Intent(this, SignIn.class);
            //GET THE IDUSER TO DISPLAY HIS HOUSES IN THE FOLLOWING ACTIVITY
            intent.putExtra("IdUser", newUser.getId());
            //START THE ACTIVITY
            startActivity(intent);
        }
    }

    //ACTION TO THE TEXTVIEW AT THE BOTTOM OF THE ACTIVITY
    public void register_link(View view) {
        //DEFINE THE ACTIVITY
        Intent intent = new Intent(this, SignIn.class);
        //START ACTIVITY
        startActivity(intent);
    }

    public Boolean validate(String username, String lastname, String firstname, String password, String email) {
        // IF A FIELD IS EMPTY, YOU CAN NOT CREATE A NEW USER
        if(username.isEmpty() || lastname.isEmpty() || firstname.isEmpty() || password.isEmpty() || email.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        // IF A USER ALREADY EXIST IN THE DATABASE, YOU HAVE TO CHOOSE AN OTHER USERNAME
        if (HouseSellingDatabase.getInstance(this).userDAO().getUsername(username) != null && HouseSellingDatabase.getInstance(this).userDAO().getUsername(username).equals(username))
        {
            Toast.makeText(this, "Username already token", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    //TO REDIRECT MENUS TO THE RIGHT PAGE THANKS TO THE ID OF THE ITEM OF THE NAVIGATION DRAWER
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                Intent intent1 = new Intent(MainActivity.this, Languages.class);
                startActivity(intent1);
                return true;

            case R.id.item_about:
                Intent intent2 = new Intent(MainActivity.this, About.class);
                startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent3);
                return true;

            default:
                return false;
        }
    }

    // ADD LIKE THE REAL ANDROID PHONE WHEN YOU LEAVE AN APP BY PRESSING THE RETURN BUTTON.
    // YOU HAVE TO TAP TWICE TO EXIT THE APP
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        // IF THE USER HAS ALREADY PRESS ONCE, YOU GO HERE
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        // IF THE USER WAIT MORE THAN 2 SECONDS, HE HAS TO TAP TWICE AGAIN
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }

        }, 2000);
    }
}