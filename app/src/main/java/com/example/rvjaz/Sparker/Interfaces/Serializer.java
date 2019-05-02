package com.example.rvjaz.Sparker.Interfaces;


import com.google.gson.annotations.SerializedName;

public class Serializer {



    @SerializedName("id")
    public int ID;
    @SerializedName("plateNumber")
    public String Platenumber;
    @SerializedName("battery")
    public String Battery;
    @SerializedName("distance")
    public double Distance;
    @SerializedName("charging")
    public Boolean Charging;
    @SerializedName("latitude")
    public double Latitude;
    @SerializedName("longitude")
    public double Longitute;
    @SerializedName("adress")
    public String Adress;
    @SerializedName("title")
    public String Title;
    @SerializedName("photo")
    public String Photo;
    }