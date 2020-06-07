package com.example.dexemon_t3.models.typesCommon;

import java.util.ArrayList;

public class types2Common {
    ArrayList<types1Common> pokemon;

    public types2Common(ArrayList<types1Common> pokemon) {
        this.pokemon = pokemon;
    }

    public ArrayList<types1Common> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<types1Common> pokemon) {
        this.pokemon = pokemon;
    }
}
