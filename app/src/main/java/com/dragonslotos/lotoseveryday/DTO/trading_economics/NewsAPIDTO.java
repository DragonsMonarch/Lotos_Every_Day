package com.dragonslotos.lotoseveryday.DTO.trading_economics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsAPIDTO {
    @Expose
    @SerializedName("totalResults")
    public int totalResults;
    @Expose
    @SerializedName("status")
    public String status;
    @Expose
    @SerializedName("articles")
    public List<Atricles> articles;

    public static class Atricles{
        @Expose
        @SerializedName("source")
        public Source source;
        @Expose
        @SerializedName("title")
        public String title;
        @Expose
        @SerializedName("author")
        public String author;
        @Expose
        @SerializedName("description")
        public String description;
        @Expose
        @SerializedName("url")
        public String url;
        @Expose
        @SerializedName("publishedAt")
        public String publishedAt;

        @Override
        public String toString() {
            return "Atricles{" +
                    "source=" + source +
                    ", title='" + title + '\'' +
                    ", author='" + author + '\'' +
                    ", description='" + description + '\'' +
                    ", url='" + url + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    '}';
        }
    }
    public static class Source {
        @Expose
        @SerializedName("name")
        public String name;
        @Expose
        @SerializedName("id")
        public String id;

        @Override
        public String toString() {
            return "Source{" +
                    "name='" + name + '\'' +
                    ", id='" + id + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "NewsAPIDTO{" +
                "totalResults=" + totalResults +
                ", status='" + status + '\'' +
                ", articles=" + articles.toString() +
                '}';
    }
}