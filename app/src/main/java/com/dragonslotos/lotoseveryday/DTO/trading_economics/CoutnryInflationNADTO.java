package com.dragonslotos.lotoseveryday.DTO.trading_economics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CoutnryInflationNADTO {

    @Expose
    @SerializedName("yearly_rate_pct")
    public double yearly_rate_pct;
    @Expose
    @SerializedName("monthly_rate_pct")
    public double monthly_rate_pct;
    @Expose
    @SerializedName("period")
    public String period;
    @Expose
    @SerializedName("type")
    public String type;
    @Expose
    @SerializedName("country")
    public String country;

    @Override
    public String toString() {
        return "CoutnryInflationNADTO{" +
                "yearly_rate_pct=" + yearly_rate_pct +
                ", monthly_rate_pct=" + monthly_rate_pct +
                ", period='" + period + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
