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
import com.example.nico.projet.Model.User;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_register);
        navigationView.setNavigationItemSelectedListener(this);

    }

    // Method for the action of the button
    public void register(View view){

        EditText eusername = (EditText)findViewById(R.id.username_register);
        String username = eusername.getText().toString();

        EditText elastname = (EditText)findViewById(R.id.lastname_register);
        String lastname = elastname.getText().toString();

        EditText efirstname = (EditText)findViewById(R.id.lastname_register);
        String firstname = efirstname.getText().toString();

        EditText epassword = (EditText)findViewById(R.id.password_register);
        String password = epassword.getText().toString();

        EditText eemail = (EditText)findViewById(R.id.email_register);
        String email = eemail.getText().toString();



        if(validate(username, lastname, firstname, password, email)){

            User newUser = new User(firstname, lastname, username, password, email);
            HouseSellingDatabase.getInstance(this).userDAO().insertUser(newUser);


            Intent intent = new Intent(this, SignIn.class);

            intent.putExtra("IdUser", newUser.getId());
            startActivity(intent);
        }


        //HouseSellingDatabase.getInstance(this).userDAO().insertUser(new User(firstname, lastname, username, password, email));


        //Intent intent = new Intent(this, ListOfActivity.class);
        //startActivity(intent);
    }

    public void register_link(View view){
        Intent intent = new Intent(this, SignIn.class);
        startActivity(intent);
    }


    public Boolean validate(String username, String lastname, String firstname, String password, String email)
    {

        boolean ok = true;


        if(username.isEmpty() || lastname.isEmpty() || firstname.isEmpty() || password.isEmpty() || email.isEmpty()){
            Toast.makeText(this,"Please enter all the details", Toast.LENGTH_SHORT).show();
            ok = false;
        }

        if (HouseSellingDatabase.getInstance(this).userDAO().getUsername(username) != null && HouseSellingDatabase.getInstance(this).userDAO().getUsername(username).equals(username)) {
            Toast.makeText(this, "Username already token", Toast.LENGTH_SHORT).show();
            ok = false;
        }

        return ok;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                //Intent intent1 = new Intent(MainActivity.this, Languages.class);
                //startActivity(intent1);
                return true;

            case R.id.item_about:
                //Intent intent2 = new Intent(MainActivity.this, About.class);
                //startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(MainActivity.this, SignIn.class);
                startActivity(intent3);
                return true;


            default:
                return false;
        }
    }
}