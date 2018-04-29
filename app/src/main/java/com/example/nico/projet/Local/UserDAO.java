package com.example.nico.projet.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nico.projet.Model.User;

import java.util.List;

// DETERMINE THE METHODS AND ACTION THEY WILL HAVE ON THE DB
// NORMALLY EVERYTHING WITH @QUERY, BUT @INSERT... ARE THERE TO SIMPLIFY

@Dao // ANNOUNCES THAT THE CLASS IS IN DAO
public interface UserDAO
{

    @Query("SELECT * FROM user WHERE id=:userId")
    User getUserById(int userId);


    // METHOD FOR THE LOGIN
    @Query("SELECT * FROM user WHERE Username = :username AND Password = :password")
    User getUser(String username, String password);

    // METHOD TO CHECK DOUBLET
    @Query("SELECT Username FROM user WHERE Username = :username")
    String getUsername(String username);

    @Insert
    void insertUser(User... users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUsers();
}