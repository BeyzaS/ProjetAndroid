package com.example.nico.projet.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nico.projet.Model.User;

import java.util.List;

// import io.reactivex.Flowable;


// DETERMINE METHODES, ET L'ACTION QU'ELLES VONT AVOIR SUR LA DB
// NORMALEMENT TOUT AVEC @QUERY, MAIS @INSERT ... SONT LA POUR SIMPLIFIER

@Dao // ANNONCE QUE LA CLASSE EST EN DAO
public interface UserDAO
{

    @Query("SELECT * FROM user WHERE id=:userId")
    User getUserById(int userId);


    // METHODE POUR LE LOGIN
    @Query("SELECT * FROM user WHERE Username = :username AND Password = :password")
    User getUser(String username, String password);

    // METHODE POUR VERIFIER LES DOUBLONS
    @Query("SELECT Username FROM user WHERE Username = :username")
    String getUsername(String username);


    //@Query("UPDATE user SET Id=:idUser, Firstname=:firstname, Lastname=:lastname, Username=:username, Password=:password, Email=:email WHERE id=:idUser")
    //User updateUser(int idUser, String firstname, String lastname, String username, String password, String email);

    @Insert
    void insertUser(User... users);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUsers();
}