package com.example.dexemon_t3.pokeapi;

import com.example.dexemon_t3.models.types_frag.fragTypes2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface typesApiService {
    @GET("type/{pokeType}")
    Call<fragTypes2> getTypes(@Path("pokeType")String pokeType);

}
