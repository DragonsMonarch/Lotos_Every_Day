package com.dragonslotos.lotoseveryday.DTO.trading_economics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CountrysTeDTO {

    public CountrysTeDTO(){}
    public CountrysTeDTO(String country, String Continent, String Group,
                  String ISO3, String ISO2){
        this.Country = country;
        this.Continent = Continent;
        this.Group = Group;
        this.ISO3 = ISO3;
        this.ISO2 = ISO2;

    }

    @SerializedName("Country")
    @Expose
    private String Country;
    @SerializedName("Continent")
    @Expose
    private String Continent;
    @SerializedName("Group")
    @Expose
    private String Group;
    @SerializedName("ISO3")
    @Expose
    private String ISO3;
    @SerializedName("ISO2")
    @Expose
    private String ISO2;

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getContinent() {
        return Continent;
    }

    public void setContinent(String continent) {
        Continent = continent;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getISO3() {
        return ISO3;
    }

    public void setISO3(String ISO3) {
        this.ISO3 = ISO3;
    }

    public String getISO2() {
        return ISO2;
    }

    public void setISO2(String ISO2) {
        this.ISO2 = ISO2;
    }

    @Override
    public String toString() {
        return "CountryDTO{" +
                "Country='" + Country + '\'' +
                ", Continent='" + Continent + '\'' +
                ", Group='" + Group + '\'' +
                ", ISO3='" + ISO3 + '\'' +
                ", ISO2='" + ISO2 + '\'' +
                '}';
    }
}