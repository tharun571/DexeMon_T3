package com.example.dexemon_t3.models.types_frag;

import java.util.ArrayList;

public class fragTypes2 {
    ArrayList<fragTypes1> pokemon;

    public fragTypes2(ArrayList<fragTypes1> pokemon) {
        this.pokemon = pokemon;
    }

    public ArrayList<fragTypes1> getPokemon() {
        return pokemon;
    }

    public void setPokemon(ArrayList<fragTypes1> pokemon) {
        this.pokemon = pokemon;
    }
}
