package com.example.dexemon_t3.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
import com.example.dexemon_t3.models.FragmentModels.info1;
import com.example.dexemon_t3.models.Pokemon;
import com.example.dexemon_t3.models.types_frag.fragTypes1;

import java.util.ArrayList;
import java.util.List;

public class typesFragAdapter extends RecyclerView.Adapter<typesFragAdapter.ViewHolder> implements Filterable {


    private static final String TAG = "drtyujbvfn";
    private ArrayList<fragTypes1> dataset;
    private ArrayList<fragTypes1> datasetFull;
    private Context context;
    String name,ur;



    public typesFragAdapter(Context context) {
        this.context=context;
        dataset=new ArrayList<>();
        datasetFull=new ArrayList<>();

    }
    @NonNull
    @Override
    public typesFragAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_type,parent,false);


        return new typesFragAdapter.ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull typesFragAdapter.ViewHolder holder, int position) {

        int a=position+1;
         name=dataset.get(position).getPokemon().getName();
        Log.w(TAG,"OREW "+name);

        if(a<10) {
            holder.no.setText("#00" + a);
        }
        else if(a<100){
            holder.no.setText("#0" + a);
        }
        else {
            holder.no.setText("#" + a);
        }

        a++;
        holder.text.setText(name);
        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+dataset.get(position).getPokemon().getNumber()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);


        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                new getValues2(name,"https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+dataset.get(position).getPokemon().getNumber()+".png",dataset.get(position).getPokemon().getNumber()-1);

                Intent intent=new Intent(context, info.class);
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<fragTypes1> in){

        dataset.addAll(in);
        datasetFull.addAll(in);
        notifyDataSetChanged();


    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView no,text;
        ImageView image;
        ItemClickListener itemClickListener;
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            no=(TextView)itemView.findViewById(R.id.no_type);
            text=(TextView)itemView.findViewById(R.id.notextview_type);
            image=(ImageView)itemView.findViewById(R.id.fotoImageView_type);

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


            ArrayList<fragTypes1> filteredList= new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredList.addAll(datasetFull);
            }
            else{
                String filterPattern =constraint.toString().toLowerCase().trim();

                for(fragTypes1 item :datasetFull){

                    if(item.getPokemon().getName().contains(filterPattern)){
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
