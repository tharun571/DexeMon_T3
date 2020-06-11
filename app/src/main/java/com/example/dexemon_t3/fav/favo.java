package com.example.dexemon_t3.fav;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class favo {

    @PrimaryKey(autoGenerate = true)
    private int id;


    private int no;

    private String name;
    private String url;

    public favo(int no, String name, String url) {
        this.no = no;
        this.name = name;
        this.url = url;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
