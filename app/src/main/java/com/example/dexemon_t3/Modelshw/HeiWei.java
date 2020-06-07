package com.example.dexemon_t3.Modelshw;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HeiWei {
    int height;
    int weight;
    ArrayList<stat> stats;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<stat> getStats() {
        return stats;
    }

    public void setStats(ArrayList<stat> stats) {
        this.stats = stats;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
