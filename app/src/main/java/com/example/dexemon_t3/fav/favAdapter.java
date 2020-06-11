package com.example.dexemon_t3.fav;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dexemon_t3.R;
import com.example.dexemon_t3.models.Pokemon;

import java.util.ArrayList;
import java.util.List;

public class favAdapter extends RecyclerView.Adapter<favAdapter.ViewHolder> {

    List<favo> dataset;
    Context context;

    public favAdapter(Context context) {
        this.context=context;
        dataset=new ArrayList<>();


    }


    @NonNull
    @Override
    public favAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull favAdapter.ViewHolder holder, int position) {
        favo fav=dataset.get(position);

        holder.no.setText(fav.getId());
        holder.name.setText(fav.getName());

        Glide.with(context)
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+fav.getId()+".png")
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.image);




    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
    public void setNotes(List<favo> notes) {
        this.dataset = notes;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,no;
        ImageView image;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name=(TextView)itemView.findViewById(R.id.notextview_fav);
            no=(TextView)itemView.findViewById(R.id.no_fav);
            image=(ImageView)itemView.findViewById(R.id.fotoImageView_fav);
        }
    }
}
