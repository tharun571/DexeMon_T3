package com.example.dexemon_t3.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dexemon_t3.Adapters.commonAdapter;
import com.example.dexemon_t3.Adapters.typesFragAdapter;
import com.example.dexemon_t3.Interface.FragmentComm;
import com.example.dexemon_t3.LoadingDialog;
import com.example.dexemon_t3.R;
import com.example.dexemon_t3.models.types_frag.fragTypes1;
import com.example.dexemon_t3.models.types_frag.fragTypes2;
import com.example.dexemon_t3.pokeapi.typesApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.dexemon_t3.Adapters.commonAdapter.EXTRA_P;
import static com.example.dexemon_t3.Adapters.commonAdapter.EXTRA_P1;


public class TypesFrag extends Fragment {

    private static final String TAG = "sdfghuijhgf";

    String s1,s2;


    TextView title;
    Retrofit retrofit;

    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_types,container,false);

        final LoadingDialog dialog= new LoadingDialog(getActivity());

        s1=Example.n;s2=Example.u;
        //Log.w(TAG,"LOP "+s1+s2);

        title=(TextView)view.findViewById(R.id.title_type);
        title.setText(s1.toUpperCase());

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dialog.startLoading();

        final typesFragAdapter adapter=new typesFragAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_type);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        typesApiService service=retrofit.create(typesApiService.class);
        Call<fragTypes2> call=service.getTypes(s1);

        call.enqueue(new Callback<fragTypes2>() {
            @Override
            public void onResponse(Call<fragTypes2> call, Response<fragTypes2> response) {
                dialog.stopLoading();
                fragTypes2 fragTypes2=response.body();
                ArrayList<fragTypes1> fragTypes1ArrayList=fragTypes2.getPokemon();

                adapter.add(fragTypes1ArrayList);

            }

            @Override
            public void onFailure(Call<fragTypes2> call, Throwable t) {

            }
        });

        searchView=(SearchView)view.findViewById(R.id.searchv2);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        return view;
    }
}
