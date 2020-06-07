package com.example.dexemon_t3.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexemon_t3.Fragments.Example1;
import com.example.dexemon_t3.Fragments.TypesFrag;
import com.example.dexemon_t3.Interface.ItemClickListener;
import com.example.dexemon_t3.R;
import com.example.dexemon_t3.info;
import com.example.dexemon_t3.models.types.types1;
import com.example.dexemon_t3.models.types.types2;

import java.util.ArrayList;

public class typesAdapter extends RecyclerView.Adapter<typesAdapter.ViewHolder>{

    private ArrayList<types2> dataset;
    private Context context;

    public typesAdapter(Context context) {
        this.context=context;
        dataset=new ArrayList<>();
    }

    @NonNull
    @Override
    public typesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item,parent,false);




        return new ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull typesAdapter.ViewHolder holder, int position) {
        types2 t=dataset.get(position);

        String name1=t.getType().getName();

        if(name1.equals("fire")){
            holder.notextview.setTextColor(Color.parseColor("#FF6D00"));
        }
        else if(name1.equals("grass")){
            holder.notextview.setTextColor(Color.parseColor("#76FF03"));
        }
        else if(name1.equals("poison")){
            holder.notextview.setTextColor(Color.parseColor("#9C27B0"));
        }
        else if(name1.equals("water")){
            holder.notextview.setTextColor(Color.parseColor("#2196f3"));
        }
        else if(name1.equals("electric")){
            holder.notextview.setTextColor(Color.parseColor("#ffea00"));
        }
        else if(name1.equals("psychic")){
            holder.notextview.setTextColor(Color.parseColor("#ec407a"));
        }
        else if(name1.equals("ice")){
            holder.notextview.setTextColor(Color.parseColor("#4dd0e1"));
        }
        else if(name1.equals("dragon")){
            holder.notextview.setTextColor(Color.parseColor("#3f51b5"));
        }
        else if(name1.equals("dark")){
            holder.notextview.setTextColor(Color.parseColor("#4e342e"));
        }
        else if(name1.equals("fairy")){
            holder.notextview.setTextColor(Color.parseColor("#f48fb1"));
        }
        else if(name1.equals("normal")){
            holder.notextview.setTextColor(Color.parseColor("#9e9e9e"));
        }
        else if(name1.equals("fighting")){
            holder.notextview.setTextColor(Color.parseColor("#b71c1c"));
        }
        else if(name1.equals("flying")){
            holder.notextview.setTextColor(Color.parseColor("#b39ddb"));
        }
        else if(name1.equals("ground")){
            holder.notextview.setTextColor(Color.parseColor("#ffab40"));
        }
        else if(name1.equals("rock")){
            holder.notextview.setTextColor(Color.parseColor("#8d6e63"));
        }
        else if(name1.equals("bug")){
            holder.notextview.setTextColor(Color.parseColor("#c6ff00"));
        }
        else if(name1.equals("ghost")){
            holder.notextview.setTextColor(Color.parseColor("#6200ea"));
        }
        else if(name1.equals("steel")){
            holder.notextview.setTextColor(Color.parseColor("#bdbdbd"));
        }






        name1 = name1.substring(0, 1).toUpperCase() + name1.substring(1).toLowerCase();
        holder.notextview.setText(""+name1);



        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                String s= dataset.get(position).getType().getName();


                new Example1(s,"chumma kizhi");
                info dex=(info)view.getContext();

                dex.getSupportFragmentManager().beginTransaction().replace(R.id.transfer,new TypesFrag()).commit();

            }
        });




    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<types2> items){

        dataset.addAll(items);

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

        private TextView notextview;



        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ViewHolder(View itemView)
        { super(itemView);

            notextview=(TextView) itemView.findViewById(R.id.item_text);



            itemView.setOnClickListener(this);



        }


        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v,getAdapterPosition());
        }
    }
}
