package com.example.dexemon_t3.fav;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface favDao {

    @Insert
    void insert(favo fa);

    @Update
    void update(favo fa);


    @Query("SELECT * FROM note_table")
    List<favo> getAll();

}
