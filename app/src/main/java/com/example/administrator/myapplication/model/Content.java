package com.example.administrator.myapplication.model;

/**
 * Created by Administrator on 2018/9/17.
 */

public class Content {

    private String imgUrl;
    private String titel;
    private String date;

    public Content(String imgUrl, String titel, String date) {
        this.imgUrl = imgUrl;
        this.titel = titel;
        this.date = date;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
