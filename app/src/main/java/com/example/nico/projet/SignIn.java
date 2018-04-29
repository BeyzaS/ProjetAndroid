package com.example.nico.projet;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nico.projet.Local.HouseSellingDatabase;
import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.User;
import com.example.nico.projet.R;


import java.io.IOException;
import java.util.List;

public class SignIn extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //TO ADD THE NAVIGATION DRAWER TO THE ACTIVITY
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationdrawer_signin);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void sign_in(View view) {
        //GET THE VALUE OF USERNAME ENTERED BY THE SELLER
        EditText eusername = (EditText)findViewById(R.id.username_signin);
        String username = eusername.getText().toString();

        //GET THE VALUE OF PASSWORD ENTERED BY THE SELLER
        EditText epassword = (EditText)findViewById(R.id.password_signin);
        String password = epassword.getText().toString();

        //ACCESS TO THE USER THANKS TO HIS PASSWORD AND USERNAME
        User user = HouseSellingDatabase.getInstance(this).userDAO().getUser(username, password);

        //IF THE USER IT NOT NULL, GO TO THE NEXT ACTIVITY WHICH IS THE LIST OF HOUSES
        if (user != null) {
            Intent intent = new Intent(this, ListOfActivity.class);
            intent.putExtra("IdUser", user.getId());
            startActivity(intent);
        }
        //OTHERWISE SHOW THE FOLLOWING MESSAGE : "User doesn't exists"
        else {
            Toast.makeText(this,"User doesn't exist", Toast.LENGTH_SHORT).show();
        }
    }

    //WHEN THE SELLER CLICK ON THE TEXTVIEW "Dont' have an account yet ? ...",
    // HE IS REDIRECTED TO THE MAIN ACTIVITY (REGISTER)
    public void sign_in_link(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    //TO REDIRECT MENUS TO THE RIGHT PAGE THANKS TO THE ID OF THE ITEM OF THE NAVIGATION DRAWER
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_languages:
                Intent intent1 = new Intent(SignIn.this, Languages.class);
                startActivity(intent1);
                return true;

            case R.id.item_about:
                Intent intent2 = new Intent(SignIn.this, About.class);
                startActivity(intent2);
                return true;

            case R.id.item_logout:
                Intent intent3 = new Intent(SignIn.this, SignIn.class);
                startActivity(intent3);
                return true;

            default:
                return false;
        }
    }

    //ACTION TO THE BACK BUTTON
    public void onBackPressed() {
        Intent intent = new Intent(SignIn.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
