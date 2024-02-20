package com.dragonslotos.lotoseveryday.DTO.trading_economics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GDPTeDTO {


    @Expose
    @SerializedName("PreviousValueDate")
    public String PreviousValueDate;
    @Expose
    @SerializedName("PreviousValue")
    public double PreviousValue;
    @Expose
    @SerializedName("FirstValueDate")
    public String FirstValueDate;
    @Expose
    @SerializedName("CreateDate")
    public String CreateDate;
    @Expose
    @SerializedName("HistoricalDataSymbol")
    public String HistoricalDataSymbol;
    @Expose
    @SerializedName("Frequency")
    public String Frequency;
    @Expose
    @SerializedName("Adjustment")
    public String Adjustment;
    @Expose
    @SerializedName("CategoryGroup")
    public String CategoryGroup;
    @Expose
    @SerializedName("URL")
    public String URL;
    @Expose
    @SerializedName("Unit")
    public String Unit;
    @Expose
    @SerializedName("SourceURL")
    public String SourceURL;
    @Expose
    @SerializedName("Source")
    public String Source;
    @Expose
    @SerializedName("LatestValue")
    public double LatestValue;
    @Expose
    @SerializedName("LatestValueDate")
    public String LatestValueDate;
    @Expose
    @SerializedName("Title")
    public String Title;
    @Expose
    @SerializedName("Category")
    public String Category;
    @Expose
    @SerializedName("Country")
    public String Country;

    @Override
    public String toString() {
        return "GDPTeDTO{" +
                "PreviousValueDate='" + PreviousValueDate + '\'' +
                ", PreviousValue=" + PreviousValue +
                ", FirstValueDate='" + FirstValueDate + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                ", HistoricalDataSymbol='" + HistoricalDataSymbol + '\'' +
                ", Frequency='" + Frequency + '\'' +
                ", Adjustment='" + Adjustment + '\'' +
                ", CategoryGroup='" + CategoryGroup + '\'' +
                ", URL='" + URL + '\'' +
                ", Unit='" + Unit + '\'' +
                ", SourceURL='" + SourceURL + '\'' +
                ", Source='" + Source + '\'' +
                ", LatestValue=" + LatestValue +
                ", LatestValueDate='" + LatestValueDate + '\'' +
                ", Title='" + Title + '\'' +
                ", Category='" + Category + '\'' +
                ", Country='" + Country + '\'' +
                '}';
    }
}
