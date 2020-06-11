package com.example.dexemon_t3.Adapters;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dexemon_t3.Interface.ItemClickListener;
import com.example.dexemon_t3.R;
import com.example.dexemon_t3.getValues2;
import com.example.dexemon_t3.info;
import com.example.dexemon_t3.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> implements Filterable {
    private ArrayList<Pokemon> dataset;
    private ArrayList<Pokemon> datasetFull;
    private Context context;

    public static final String EXTRA_P1="POKEDEX1";
    public static final String EXTRA_P2="POKEDEX2";
    public static final String EXTRA_P3="POKEDEX3";

    private static final String TAG="POKEDEX";

    Pair[] pairs=new Pair[2];
    Activity activity=new Activity();




    public ListaPokemonAdapter(Context context) {
        this.context=context;
        dataset=new ArrayList<>();
        datasetFull=new ArrayList<>();
    }

    public Pokemon getposat(int position){

        return dataset.get(position);

    }

    public Pokemon getPokeat(int position){




        return dataset.get(position);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon,parent,false);




        return new ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
    final Pokemon p=dataset.get(position);
    final String sno=String.valueOf(p.getNumber());


    int a=p.getNumber();



    String name  = p.getName();
    name = name.substring(0,1).toUpperCase() + name.substring(1).toLowerCase();


    holder.notextview.setText(name);
    if(a<10) {
        holder.no.setText("#00" + p.getNumber());
    }
    else if(a<100){
        holder.no.setText("#0" + p.getNumber());
    }
    else {
        holder.no.setText("#" + p.getNumber());
    }


          Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                  .centerCrop()
                  .crossFade()
                  .diskCacheStrategy(DiskCacheStrategy.ALL)
                  .into(holder.fotoImageView);

          //event


        activity= (Activity) context;


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {

                final Pokemon p=dataset.get(position);




                String name1  = p.getName();
                name1 = name1.substring(0,1).toUpperCase() + name1.substring(1).toLowerCase();

                holder.notextview.setText(name1);
                Glide.with(context)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                        .centerCrop()
                        .crossFade()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(holder.fotoImageView);




                Intent intent=new Intent(context, info.class);




                new getValues2(dataset.get(position).getName(),"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png",position);

                pairs[0]=new Pair<View,String>(holder.fotoImageView,"poke_image");
                pairs[1]=new Pair<View,String>(holder.notextview,"poke_name");

                ActivityOptions options= null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation(activity,pairs);
                }


                context.startActivity(intent,options.toBundle());



            }
        });







    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarlistapokemon(ArrayList<Pokemon> listapokemon) {

        dataset.addAll(listapokemon);
        datasetFull.addAll(listapokemon);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ImageView fotoImageView;
        private TextView notextview;
        private TextView no;
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ViewHolder(View itemView)
           { super(itemView);
           fotoImageView=(ImageView) itemView.findViewById(R.id.fotoImageView);
           notextview=(TextView) itemView.findViewById(R.id.notextview);

           no=(TextView) itemView.findViewById(R.id.no);

           itemView.setOnClickListener(this);


           }

        @Override
        public void onClick(View v) {

            itemClickListener.OnClick(v,getAdapterPosition());

        }
    }

    @Override
    public Filter getFilter(){

        return exampleFilter;
    }

    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            ArrayList<Pokemon> filteredList= new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredList.addAll(datasetFull);
            }
            else{
                String filterPattern =constraint.toString().toLowerCase().trim();

                for(Pokemon item :datasetFull){

                    if(item.getName().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;

        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            dataset.clear();
            dataset.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
