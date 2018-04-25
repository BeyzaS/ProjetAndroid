package com.example.nico.projet;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class Languages extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_languages);
    }

    public void changelanguage(View view){

        Configuration config = new Configuration(getResources().getConfiguration());

        switch (view.getId())
        {
            case R.id.swiss:
                config.setLocale(Locale.FRENCH);
                break;
            case R.id.british:
                config.setLocale(Locale.ENGLISH);
                break;
        }
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());
    }



}
