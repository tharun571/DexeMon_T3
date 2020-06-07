package com.example.dexemon_t3.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Pokemon {

    private int number;
    @SerializedName("name")
    private String name;
    private String url;





    public Pokemon(){

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlp=url.split("/");
        return Integer.parseInt(urlp[urlp.length -1]);
    }

    public void setNumber(int number) {
        this.number = number;
    }


}
