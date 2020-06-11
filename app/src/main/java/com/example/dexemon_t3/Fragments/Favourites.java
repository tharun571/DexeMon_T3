package com.example.dexemon_t3.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dexemon_t3.R;
import com.example.dexemon_t3.fav.favAdapter;
import com.example.dexemon_t3.fav.favDatabase;
import com.example.dexemon_t3.fav.favo;

import java.util.List;


public class Favourites extends Fragment {


    private static final String TAG = " mnbvcxzasd";






    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_favourites, container, false);

        favDatabase dtb= favDatabase.getInstance(getActivity());
        dtb.favdao().getAll();






        RecyclerView recyclerView = view.findViewById(R.id.recycler_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        final favAdapter adapter = new favAdapter(getActivity());
        recyclerView.setAdapter(adapter);





        return view;
    }


}
