package com.example.nico.projet.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "location")
public class Location {


    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @NonNull
    @ColumnInfo(name = "Description")
    private String description;

    @NonNull
    @ColumnInfo(name = "Town")
    private String town;

    @NonNull
    @ColumnInfo(name = "HasSwimmingPool")
    private boolean hasSwimmingPool;

    @NonNull
    @ColumnInfo(name = "HasCinema")
    private boolean hasCinema;

    @NonNull
    @ColumnInfo(name = "HasSportCenter")
    private boolean hasSportCenter;

    public Location(String description, String town, boolean hasSwimmingPool, boolean hasCinema, boolean hasSportCenter) {
        this.description = description;
        this.town = town;
        this.hasSwimmingPool = hasSwimmingPool;
        this.hasCinema = hasCinema;
        this.hasSportCenter = hasSportCenter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public boolean isHasSwimmingPool() {
        return hasSwimmingPool;
    }

    public void setHasSwimmingPool(boolean hasSwimmingPool) {
        this.hasSwimmingPool = hasSwimmingPool;
    }

    public boolean isHasCinema() {
        return hasCinema;
    }

    public void setHasCinema(boolean hasCinema) {
        this.hasCinema = hasCinema;
    }

    public boolean isHasSportCenter() {
        return hasSportCenter;
    }

    public void setHasSportCenter(boolean hasSportCenter) {
        this.hasSportCenter = hasSportCenter;
    }

    @Override
    public String toString() {

        String swimmingpool;
        if(hasSwimmingPool)
        {
            swimmingpool = "Has swimming pool";
        }
        else{
            swimmingpool = "No swimming pool";
        }

        String cinema;
        if(hasCinema)
        {
            cinema = "Has cinema";
        }
        else{
            cinema = "No cinema";
        }

        String sportcenter;
        if(hasSportCenter)
        {
            sportcenter = "Has sport center";
        }
        else{
            sportcenter = "No sport center";
        }

        return
                description +
                "\n" + town +
                        "\n" + swimmingpool +
                        "\n" + cinema +
                        "\n" + sportcenter;
    }
}
