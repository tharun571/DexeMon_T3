package com.example.dexemon_t3.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexemon_t3.Dexboard;

import com.example.dexemon_t3.Fragments.Example1;
import com.example.dexemon_t3.Fragments.TypesFrag;
import com.example.dexemon_t3.Fragments.common;
import com.example.dexemon_t3.Interface.FragmentComm;
import com.example.dexemon_t3.Interface.ItemClickListener;
import com.example.dexemon_t3.R;
import com.example.dexemon_t3.models.FragmentModels.info1;
import com.example.dexemon_t3.models.Pokemon;
import com.example.dexemon_t3.models.types.types2;

import java.util.ArrayList;
import java.util.List;

public class commonAdapter extends RecyclerView.Adapter<commonAdapter.ViewHolder> implements Filterable {


    private static final String TAG = "sdfghjklbhyu7";
    public static final String EXTRA_P = "sdfghjklbhyu712345";
    public static final String EXTRA_P1 = "jklbhyu712345";

    private ArrayList<info1> dataset;
    private ArrayList<info1> datasetFull;
    private Context context;
    String info,value,url;

    public commonAdapter(Context context) {
        this.context=context;
        dataset=new ArrayList<>();
        datasetFull=new ArrayList<>();

    }


    @NonNull
    @Override
    public commonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_common,parent,false);


        return new ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull commonAdapter.ViewHolder holder, int position) {

        info1 p=dataset.get(position);

        String name1=p.getName();
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
        Log.w(TAG,"XSE 1 "+name1);
        holder.notextview.setText(name1);



        holder.setFragmentComm(new FragmentComm() {
            @Override
            public void respond(View view, int position, String name, String url) {

                if(info.equals("type")) {
                    value = dataset.get(position).getName();
                    url = dataset.get(position).getUrl();

                    new Example1(value, url);
                    Dexboard dex = (Dexboard) view.getContext();

                    dex.getSupportFragmentManager().beginTransaction().replace(R.id.common_frag, new TypesFrag()).commit();
                }

            }
        });

            }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

           public void add(ArrayList<info1> in,String s){

            dataset.addAll(in);
            info=s;
               datasetFull.addAll(in);
            Log.w(TAG,"XSE 1");
            notifyDataSetChanged();


        }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView notextview;

        FragmentComm fragmentComm;

        public void setFragmentComm(FragmentComm fragmentComm) {
            this.fragmentComm = fragmentComm;
        }




        public ViewHolder(View itemView)
        { super(itemView);


            notextview=(TextView) itemView.findViewById(R.id.text_common);
            Log.w(TAG,"XSE 2");



            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {


            fragmentComm.respond(v,getAdapterPosition(),info,url);

        }


    }

    @Override
    public Filter getFilter(){

        return exampleFilter;
    }

    private Filter exampleFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {


            ArrayList<info1> filteredList= new ArrayList<>();
            if(constraint==null||constraint.length()==0){
                filteredList.addAll(datasetFull);
            }
            else{
                String filterPattern =constraint.toString().toLowerCase().trim();

                for(info1 item :datasetFull){

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
