package com.example.nico.projet.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.nico.projet.Model.House;
import com.example.nico.projet.Model.Location;
import com.example.nico.projet.Model.User;

import static com.example.nico.projet.Local.HouseSellingDatabase.DATABASE_VERSION;

@Database(entities = {User.class, House.class, Location.class},version = DATABASE_VERSION, exportSchema = false)

// ROOM PREVOIT LES CHANGEMENTS DE STRUCTURE DE LA TABLE USER
// ON TRAVAILLE AVEC UN FICHIER POUR LA DB
// ON GERE LES VERSIONS DES DB, CAR QUAND UN UTILISATEUR TELECHARGE NOTRE APP,
// IL OBTIENT LE "VIEUX FICHIER"

public abstract class HouseSellingDatabase extends RoomDatabase
{
    public static final int DATABASE_VERSION=3;
    public static final String DATABASE_NAME = "EDMT-Database-Room" ;

    public abstract UserDAO userDAO ();
    public abstract HouseDAO houseDAO ();
    public abstract LocationDAO locationDAO ();

    private static HouseSellingDatabase mInstance ;


    // CE BUILDER RETOURNE UN OBJECT QUI CREE D'AUTRES OBJETS
    public static HouseSellingDatabase getInstance (Context context)
    {
        if  (mInstance == null)
        {
            // A EVITER DE LE FAIRE PLUSIEURS FOIS CAR CHER COMME APPEL
            mInstance = Room.databaseBuilder(context,HouseSellingDatabase.class,DATABASE_NAME) // REOUTNRE UN BUILDER
                    .allowMainThreadQueries() // LANCE DES REQUETES DANS LA DB DEPUIS LE THREAD UI (PRINCIPAL)
                    .fallbackToDestructiveMigration() // REOUTNRE UN BUILDER PARAMéTRER
                    .build(); // RETOURN UNE INSTANCE DE TYPE USERDATABASE
        }
        return mInstance ;
    }

    public void clearAllTables ()
    {

    }
}
