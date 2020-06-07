package com.example.dexemon_t3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dexemon_t3.Adapters.ItemsAdapter;
import com.example.dexemon_t3.Adapters.ListaPokemonAdapter;
import com.example.dexemon_t3.Adapters.typesAdapter;
import com.example.dexemon_t3.ModelsItems.Item;
import com.example.dexemon_t3.ModelsItems.ItemPut;
import com.example.dexemon_t3.Modelshw.HeiWei;
import com.example.dexemon_t3.Modelshw.stat;
import com.example.dexemon_t3.models.Pokemon;
import com.example.dexemon_t3.models.Pokemonreput;
import com.example.dexemon_t3.models.types.types2;
import com.example.dexemon_t3.models.types.types3;
import com.example.dexemon_t3.pokeapi.HeiWeiService;
import com.example.dexemon_t3.pokeapi.ItemsService;
import com.example.dexemon_t3.pokeapi.TypesService;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class info extends AppCompatActivity {

    private static final String TAG = "qwertyuiop" ;
    ImageView image;
    TextView text;
    TextView stat1,stat2,stat3,stat4,stat5,stat6;

    ArrayList<Pokemon> dataset=new ArrayList<>();

    Retrofit retrofit;
    String pos;
    Call<Pokemonreput> pokemonCall;
    String name;

    TextView h,w;
    LoadingDialog dialog;

    ImageView p,n;
    int posit;
    String url,name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        image = (ImageView) findViewById(R.id.image1);
        text = (TextView) findViewById(R.id.text1);
        h=(TextView)findViewById(R.id.height);
        w=(TextView)findViewById(R.id.weight);
        stat1=(TextView)findViewById(R.id.stat1);
        stat2=(TextView)findViewById(R.id.stat2);
        stat3=(TextView)findViewById(R.id.stat3);
        stat4=(TextView)findViewById(R.id.stat4);
        stat5=(TextView)findViewById(R.id.stat5);
        stat6=(TextView)findViewById(R.id.stat6);
        p=(ImageView)findViewById(R.id.prev);
        n=(ImageView)findViewById(R.id.next);

        dialog=new LoadingDialog(this);


        name1 = getValue1.nam;
        name=name1;
        url = getValue1.ur;
        posit=(1+getValue1.no);
        pos=""+(1+getValue1.no);



        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(posit>0){
                    posit--;

                    pos=""+posit;

                    Glide.with(info.this)
                            .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(posit)+".png")
                            .centerCrop()
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(image);





                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://pokeapi.co/api/v2/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();








                    dialog.startLoading();

                    getHeiWei();
                    getTypes();




                    posit--;

                }
            }
        });


        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w(TAG,"VFD ");

                posit++;
                pos=""+posit;



                Glide.with(info.this)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+(posit)+".png")
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(image);





                retrofit = new Retrofit.Builder()
                        .baseUrl("http://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();








                dialog.startLoading();

                getHeiWei();
                getTypes();







            }
        });




        Log.w(TAG,"VFD ");






        Glide.with(this)
                .load(url)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(image);





        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();








        dialog.startLoading();

        getHeiWei();
        getTypes();





    }

    private void getTypes(){

        final typesAdapter adapter=new typesAdapter(this);
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.itemRecycler);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);



        TypesService heiWeiService=retrofit.create(TypesService.class);
        final Call<types3> types3Call=heiWeiService.getType(pos);

        types3Call.enqueue(new Callback<types3>() {
            @Override
            public void onResponse(Call<types3> call, Response<types3> response) {

                dialog.stopLoading();
                types3 t3=response.body();

                ArrayList<types2> types2ArrayList=t3.getTypes();

                adapter.add(types2ArrayList);
            }

            @Override
            public void onFailure(Call<types3> call, Throwable t) {

            }
        });





    }


    private void getHeiWei(){




        HeiWeiService heiWeiService=retrofit.create(HeiWeiService.class);
        Call<HeiWei> heiWeiCall=heiWeiService.getItem(pos);

        heiWeiCall.enqueue(new Callback<HeiWei>() {
            @Override
            public void onResponse(Call<HeiWei> call, Response<HeiWei> response) {
                HeiWei heiWei=response.body();
                int h1=heiWei.getHeight();
                int w1=heiWei.getWeight();
                float w2=w1/10;
                String na=heiWei.getName();
                name1=na;
                text.setText(na.toUpperCase());

                ArrayList<stat> stats =heiWei.getStats();
                int a1=stats.get(0).getBase_stat();
                int a2=stats.get(1).getBase_stat();
                int a3=stats.get(2).getBase_stat();
                int a4=stats.get(3).getBase_stat();
                int a5=stats.get(4).getBase_stat();
                int a6=stats.get(5).getBase_stat();

                Log.w(TAG,"NMJ "+a1+" "+a2);


                stat1.setText("Hp              : "+a6);
                stat2.setText("Attack         : "+a5);
                stat3.setText("Defence        : "+a4);
                stat4.setText("Special attack  : "+a3);
                stat5.setText("Special defence : "+a2);
                stat6.setText("Speed          : "+a1);


                h.setText(""+h1+" dm");
                w.setText(""+w2+" kg");

            }

            @Override
            public void onFailure(Call<HeiWei> call, Throwable t) {

            }
        });




    }



}