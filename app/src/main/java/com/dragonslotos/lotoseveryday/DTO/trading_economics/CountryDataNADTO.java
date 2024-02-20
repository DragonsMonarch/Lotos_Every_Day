package com.dragonslotos.lotoseveryday.DTO.trading_economics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CountryDataNADTO implements Serializable {

    @Expose
    @SerializedName("primary_school_enrollment_male")
    public String primary_school_enrollment_male;
    @Expose
    @SerializedName("refugees")
    public String refugees;
    @Expose
    @SerializedName("fertility")
    public String fertility;
    @Expose
    @SerializedName("gdp_per_capita")
    public String gdp_per_capita;
    @Expose
    @SerializedName("internet_users")
    public String internet_users;
    @Expose
    @SerializedName("pop_density")
    public String pop_density;
    @Expose
    @SerializedName("region")
    public String region;
    @Expose
    @SerializedName("pop_growth")
    public String pop_growth;
    @Expose
    @SerializedName("name")
    public String name;
    @Expose
    @SerializedName("employment_industry")
    public String employment_industry;
    @Expose
    @SerializedName("urban_population")
    public String urban_population;
    @Expose
    @SerializedName("population")
    public String population;
    @Expose
    @SerializedName("threatened_species")
    public String threatened_species;
    @Expose
    @SerializedName("secondary_school_enrollment_male")
    public String secondary_school_enrollment_male;
    @Expose
    @SerializedName("infant_mortality")
    public String infant_mortality;
    @Expose
    @SerializedName("primary_school_enrollment_female")
    public String primary_school_enrollment_female;
    @Expose
    @SerializedName("post_secondary_enrollment_male")
    public String post_secondary_enrollment_male;
    @Expose
    @SerializedName("post_secondary_enrollment_female")
    public String post_secondary_enrollment_female;
    @Expose
    @SerializedName("life_expectancy_female")
    public String life_expectancy_female;
    @Expose
    @SerializedName("exports")
    public String exports;
    @Expose
    @SerializedName("tourists")
    public String tourists;
    @Expose
    @SerializedName("forested_area")
    public String forested_area;
    @Expose
    @SerializedName("co2_emissions")
    public String co2_emissions;
    @Expose
    @SerializedName("capital")
    public String capital;
    @Expose
    @SerializedName("employment_agriculture")
    public String employment_agriculture;
    @Expose
    @SerializedName("secondary_school_enrollment_female")
    public String secondary_school_enrollment_female;
    @Expose
    @SerializedName("urban_population_growth")
    public String urban_population_growth;
    @Expose
    @SerializedName("employment_services")
    public String employment_services;
    @Expose
    @SerializedName("gdp_growth")
    public String gdp_growth;
    @Expose
    @SerializedName("iso2")
    public String iso2;
    @Expose
    @SerializedName("currency")
    public Currency currency;
    @Expose
    @SerializedName("homicide_rate")
    public String homicide_rate;
    @Expose
    @SerializedName("imports")
    public String imports;
    @Expose
    @SerializedName("unemployment")
    public String unemployment;
    @Expose
    @SerializedName("life_expectancy_male")
    public String life_expectancy_male;
    @Expose
    @SerializedName("surface_area")
    public String surface_area;
    @Expose
    @SerializedName("sex_ratio")
    public String sex_ratio;
    @Expose
    @SerializedName("gdp")
    public String gdp;

    public class Currency {
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("code")
        public String code;
    }

    public double yearly_rate_pct;
    @Override
    public String toString() {
        return "CountryDataNADTO{" +
                "primary_school_enrollment_male='" + primary_school_enrollment_male + '\'' +
                ", refugees='" + refugees + '\'' +
                ", fertility='" + fertility + '\'' +
                ", gdp_per_capita='" + gdp_per_capita + '\'' +
                ", internet_users='" + internet_users + '\'' +
                ", pop_density='" + pop_density + '\'' +
                ", region='" + region + '\'' +
                ", pop_growth='" + pop_growth + '\'' +
                ", name='" + name + '\'' +
                ", employment_industry='" + employment_industry + '\'' +
                ", urban_population='" + urban_population + '\'' +
                ", population='" + population + '\'' +
                ", threatened_species='" + threatened_species + '\'' +
                ", secondary_school_enrollment_male='" + secondary_school_enrollment_male + '\'' +
                ", infant_mortality='" + infant_mortality + '\'' +
                ", primary_school_enrollment_female='" + primary_school_enrollment_female + '\'' +
                ", post_secondary_enrollment_male='" + post_secondary_enrollment_male + '\'' +
                ", post_secondary_enrollment_female='" + post_secondary_enrollment_female + '\'' +
                ", life_expectancy_female='" + life_expectancy_female + '\'' +
                ", exports='" + exports + '\'' +
                ", tourists='" + tourists + '\'' +
                ", forested_area='" + forested_area + '\'' +
                ", co2_emissions='" + co2_emissions + '\'' +
                ", capital='" + capital + '\'' +
                ", employment_agriculture='" + employment_agriculture + '\'' +
                ", secondary_school_enrollment_female='" + secondary_school_enrollment_female + '\'' +
                ", urban_population_growth='" + urban_population_growth + '\'' +
                ", employment_services='" + employment_services + '\'' +
                ", gdp_growth='" + gdp_growth + '\'' +
                ", iso2='" + iso2 + '\'' +
                ", currency=" + currency +
                ", homicide_rate='" + homicide_rate + '\'' +
                ", imports='" + imports + '\'' +
                ", unemployment='" + unemployment + '\'' +
                ", life_expectancy_male='" + life_expectancy_male + '\'' +
                ", surface_area='" + surface_area + '\'' +
                ", sex_ratio='" + sex_ratio + '\'' +
                ", gdp='" + gdp + '\'' +
                '}';
    }
}

