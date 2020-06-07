package com.example.dexemon_t3.models.typesCommon;

import com.example.dexemon_t3.models.Pokemon;

public class types1Common {
    Pokemon pokemon;

    public types1Common(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }
}
