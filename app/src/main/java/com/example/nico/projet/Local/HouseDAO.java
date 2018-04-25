package com.example.nico.projet.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nico.projet.Model.House;

import java.util.List;

@Dao
public interface HouseDAO
{
    @Query("SELECT * FROM house WHERE Id=:houseId")
    House getHouseById(int houseId);

    @Query("SELECT * FROM house WHERE IdUser=:userId")
    House[] getHousesOfUser(int userId);

    @Query("SELECT * FROM house WHERE IdUser=:userId")
    List<House> getHousesOfUserForDelete(int userId);

    @Insert
    void insertHouse(House... houses);

    @Update
    void updateHouse(House... houses);


    @Delete
    void deleteHouse(House... houses);


    @Delete
    void deleteHouses(List<House> houses);

    @Query("DELETE FROM house")
    void deleteAllHouses();
}