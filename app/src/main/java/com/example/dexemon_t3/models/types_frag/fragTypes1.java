package com.example.dexemon_t3.models.types_frag;

import com.example.dexemon_t3.models.Pokemon;

public class fragTypes1 {
    Pokemon pokemon;

    public fragTypes1(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
