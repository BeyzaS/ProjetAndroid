package com.example.nico.projet.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nico.projet.Model.Location;

import java.util.List;

@Dao
public interface LocationDAO
{
    @Query("SELECT * FROM location WHERE id=:locationId")
    Location getLocationById(int locationId);

    @Query("SELECT Id FROM location WHERE Town=:town")
    int getLocationId(String town);

    @Insert
    void insertLocation(Location... locations);

    @Update
    void updateLocation(Location... locations);

    @Delete
    void deleteLocation(Location... locations);

    @Query("DELETE FROM location")
    void deleteAllLocations();

    @Query("SELECT Town FROM location")
    String[] getAllTown();

    @Query("SELECT * FROM location")
    List<Location> getAllTowns();
}