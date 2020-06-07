package com.example.dexemon_t3.pokeapi;



import com.example.dexemon_t3.models.Pokemonreput;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokeapiService {


    @GET("pokemon")
    Call<Pokemonreput> obtenerListPokemon(@Query("limit") int limit, @Query("offset") int offset);
}
