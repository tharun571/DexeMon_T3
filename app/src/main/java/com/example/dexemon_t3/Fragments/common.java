package com.example.dexemon_t3.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dexemon_t3.Adapters.commonAdapter;
import com.example.dexemon_t3.Dexboard;
import com.example.dexemon_t3.LoadingDialog;
import com.example.dexemon_t3.R;
import com.example.dexemon_t3.models.FragmentModels.info1;
import com.example.dexemon_t3.models.FragmentModels.info2;
import com.example.dexemon_t3.pokeapi.commonApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.dexemon_t3.Dexboard.EXTRA_P1;


public class common extends Fragment {
    private static final String TAG = "polkjnhbhuiugf";
    TextView title;

    Context context;
    Retrofit retrofit;

    SearchView searchView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Dexboard dexboard = (Dexboard) getActivity();
        final String strtext = dexboard.getData();


        View view = inflater.inflate(R.layout.fragment_common, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final LoadingDialog dialog= new LoadingDialog(getActivity());

        title = (TextView) view.findViewById(R.id.title_frag);
        title.setText(strtext.toUpperCase());

        dialog.startLoading();

        final commonAdapter adapter = new commonAdapter(getActivity());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_frag_common);

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        commonApiService service = retrofit.create(commonApiService.class);
        Call<info2> info2Call = service.getInfo(strtext);

        info2Call.enqueue(new Callback<info2>() {
            @Override
            public void onResponse(Call<info2> call, Response<info2> response) {
                dialog.stopLoading();
                info2 i2 = response.body();
                ArrayList<info1> info1ArrayList = i2.getResults();

                adapter.add(info1ArrayList, strtext);
            }

            @Override
            public void onFailure(Call<info2> call, Throwable t) {

            }
        });
        searchView=(SearchView)view.findViewById(R.id.searchv1);
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
class Example{
    public static String n;
    public static String u;


}
