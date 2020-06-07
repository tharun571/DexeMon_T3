package com.example.dexemon_t3.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexemon_t3.Interface.ItemClickListener;
import com.example.dexemon_t3.ModelsItems.Item;
import com.example.dexemon_t3.R;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private static final String TAG = "lopiugbn";
    private ArrayList<Item> dataset;
    private Context context;

    public ItemsAdapter(Context context) {
        this.context=context;
        dataset=new ArrayList<>();
    }

    @NonNull
    @Override
    public ItemsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_item,parent,false);




        return new ViewHolder(view);




    }

    @Override
    public void onBindViewHolder(@NonNull ItemsAdapter.ViewHolder holder, int position) {
        final Item i=dataset.get(position);
        Log.w(TAG,"GHJ 3");
        Log.w(TAG,"GHJ 4"+position);
        String  name1   =   i.getName();
        name1 = name1.substring(0, 1).toUpperCase() + name1.substring(1).toLowerCase();


        Log.w(TAG,"GHJ "+i.getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void add(ArrayList<Item> items){

        dataset.addAll(items);
        Log.w(TAG,"GHJ 6");
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView notextview;






        public ViewHolder(View itemView)
        { super(itemView);

            notextview=(TextView) itemView.findViewById(R.id.item_text);






        }


    }


}
