package com.example.dexemon_t3.pokeapi;

import com.example.dexemon_t3.Modelshw.HeiWei;
import com.example.dexemon_t3.models.FragmentModels.info2;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface commonApiService {

    @GET("{info}")
    Call<info2> getInfo(@Path("info")String info);
}
