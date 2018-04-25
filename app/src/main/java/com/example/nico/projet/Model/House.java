package com.example.nico.projet.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

// CORRESPOND A UNE TABLE, ET CHAMP = COLONNE


@Entity(tableName = "house")
public class House {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id")
    private int id;

    @NonNull
    @ColumnInfo(name = "Type")
    private String type;

    @NonNull
    @ColumnInfo(name = "Description")
    private String description;

    @NonNull
    @ColumnInfo(name = "SquareMeter")
    private int squareMeter;

    @NonNull
    @ColumnInfo(name = "Price")
    private int price;

    @NonNull
    @ColumnInfo(name = "Floor")
    private int floor;

    @NonNull
    @ColumnInfo(name = "Address")
    private String address;

    @NonNull
    @ForeignKey(entity = Location.class, parentColumns = "id", childColumns = "Id")
    @ColumnInfo(name = "IdLocation")
    private int idLocation;


    @NonNull
    @ForeignKey(entity = User.class, parentColumns = "id", childColumns = "Id")
    @ColumnInfo(name = "IdUser")
    private int idUser;

    public House(String type, String description, int squareMeter, int price, int floor, String address, int idLocation, int idUser) {
        this.type = type;
        this.description = description;
        this.squareMeter = squareMeter;
        this.price = price;
        this.floor = floor;
        this.address = address;
        this.idLocation = idLocation;
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSquareMeter() {
        return squareMeter;
    }

    public void setSquareMeter(int squareMeter) {
        this.squareMeter = squareMeter;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    /*
    public String toString() {
        return "House{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", squareMeter=" + squareMeter +
                ", price=" + price +
                ", floor=" + floor +
                ", address='" + address + '\'' +
                ", idLocation=" + idLocation +
                ", idUser=" + idUser +
                '}';
    }
    */

    public String toString() {
        return  "Type : " + type +
                "\nDescription : '" + description +
                "\nSquare meter : " + squareMeter +
                "\nPrice : " + price +
                "\nFloor : " + floor +
                "\nAddress : '" + address +
                "\n";
    }

}
