package com.example.dexemon_t3.pokeapi;

import com.example.dexemon_t3.ModelsItems.ItemPut;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ItemsService {

    @GET("item/{pokeNo}")
    Call<ItemPut> getItem(@Path("pokeNo")String pokeNo);
}
