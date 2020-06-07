package com.example.dexemon_t3.pokeapi;

import com.example.dexemon_t3.Modelshw.HeiWei;
import com.example.dexemon_t3.models.types.types3;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HeiWeiService {

    @GET("pokemon/{pokeNo}")
    Call<HeiWei> getItem(@Path("pokeNo")String pokeNo);

}
