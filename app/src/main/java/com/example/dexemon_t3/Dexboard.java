package com.example.dexemon_t3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


import com.example.dexemon_t3.Adapters.ListaPokemonAdapter;
import com.example.dexemon_t3.Fragments.Favourites;
import com.example.dexemon_t3.Fragments.common;
import com.example.dexemon_t3.fav.favDatabase;
import com.example.dexemon_t3.fav.favo;
import com.example.dexemon_t3.models.Pokemon;
import com.example.dexemon_t3.models.Pokemonreput;
import com.example.dexemon_t3.pokeapi.PokeapiService;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Dexboard extends AppCompatActivity {

    private static final String TAG="POKEDEX";

    private Retrofit retrofit;
    private  int offset;
    private boolean apto;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;


    SearchView searchView;

    //for pagination
    int a=0;


    public String info;

    Context context;

    public static final String EXTRA_P1="POKEDEX1";




    LoadingDialog dialog;


    ArrayList<favo>  favs;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dexboard);

        dialog=new LoadingDialog(this);

        final MediaPlayer mp= MediaPlayer.create(this,R.raw.theme);
        mp.start();

        context=getBaseContext();


        final ImageView toolbar1 = (ImageView) findViewById(R.id.Toolbar1);
         final DrawerLayout drawer =  findViewById(R.id.drawer_layout1);
        toolbar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Log.w(TAG,"BHU ");
                switch (item.getItemId()) {
                    case R.id.nav_item:
                        info="item";
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new common()).commit();
                        getSupportFragmentManager().popBackStack();




                        break;
                    case R.id.nav_type:
                        info="type";
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new common()).commit();
                        getSupportFragmentManager().popBackStack();




                        break;
                    case R.id.nav_loc:
                        info="location";
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new common()).commit();
                        getSupportFragmentManager().popBackStack();




                        break;
                    case R.id.nav_region:
                        info="region";
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new common()).commit();
                        getSupportFragmentManager().popBackStack();



                        break;

                    case R.id.nav_pok:
                        Intent intent=new Intent(Dexboard.this,Dexboard.class);
                        startActivity(intent);

                        mp.stop();


                    case R.id.nav_fav:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                                new Favourites()).commit();



                }
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });




        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter=new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0)
                {int visibleitemcount=layoutManager.getChildCount();
                    int totalitemcount=layoutManager.getItemCount();
                    int pastvisibleitems=layoutManager.findFirstVisibleItemPosition();

                    if(apto)
                    {
                        if((visibleitemcount+pastvisibleitems)>=totalitemcount){


                            apto=false;
                            offset=offset+20;
                            obtenerDatos(offset);
                        }
                    }

                }
            }
        });




        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        offset=0;
        apto=true;
        obtenerDatos(offset);






    }


    private void obtenerDatos(int offset)
    {dialog.startLoading();
        PokeapiService service=retrofit.create(PokeapiService.class);
        //pagination - loading data every 20 parts
        Call<Pokemonreput> pokemonreputcall= service.obtenerListPokemon(20,a);
        a+=20;
        pokemonreputcall.enqueue(new Callback<Pokemonreput>() {
            @Override
            public void onResponse(Call<Pokemonreput> call, Response<Pokemonreput> response) {
                apto=true;
                if(response.isSuccessful())
                {
                    dialog.stopLoading();
                    Pokemonreput Pokemonreput=response.body();
                    ArrayList<Pokemon> listapokemon =Pokemonreput.getResults();

                    listaPokemonAdapter.adicionarlistapokemon(listapokemon);



                }
                else
                {
                    Log.e(TAG," onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pokemonreput> call, Throwable t) {
                Log.e(TAG," onFailure: "+t.getMessage());
                apto=true;
            }
        });

        searchView=(SearchView)findViewById(R.id.searchv);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listaPokemonAdapter.getFilter().filter(newText);
                return false;
            }
        });

    }


public String getData(){

        return info;
}

ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        Pokemon p=listaPokemonAdapter.getposat(viewHolder.getAdapterPosition());

        Log.w(TAG,"FDA "+p.getUrl());
        Log.w(TAG,"FDA ");




        favo fav = new favo(p.getNumber(),p.getName(),p.getUrl());


        favDatabase dtb=favDatabase.getInstance(context);











    }
};




}
