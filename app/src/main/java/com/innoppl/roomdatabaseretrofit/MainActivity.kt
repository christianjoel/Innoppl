package com.innoppl.roomdatabaseretrofit;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.innoppl.roomdatabaseretrofit.Adapter.ActorAdapter;
import com.innoppl.roomdatabaseretrofit.Modal.Actor;
import com.innoppl.roomdatabaseretrofit.Network.Retrofit;
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository;
import com.innoppl.roomdatabaseretrofit.ViewModal.ActorViewModal;
import com.innoppl.roomdatabaseretrofit.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActorViewModal actorViewModal;

    private RecyclerView recyclerView;
    private List<Actor> actorList;
    private ActorRespository actorRespository;
    private ActorAdapter actorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        actorRespository = new ActorRespository(getApplication());
        actorList = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actorList);

        actorViewModal = new ViewModelProvider(this).get(ActorViewModal.class);
        networkRequest();
        actorViewModal.getAllActor().observe(this, new Observer<List<Actor>>() {
            @Override
            public void onChanged(List<Actor> actorList) {
                recyclerView.setAdapter(actorAdapter);
                actorAdapter.getAllActors(actorList);

                Log.d("main", "onChanged: " + actorList.size()+"sdfsdfsf");
            }
        });

    }

    private void networkRequest() {

        Retrofit retrofit = new Retrofit();
        Call<List<Actor>> call = retrofit.api.getAllActors();
        call.enqueue(new Callback<List<Actor>>() {
            @Override
            public void onResponse(Call<List<Actor>> call, Response<List<Actor>> response) {
                if (response.isSuccessful()) {
                    actorRespository.insert(response.body());
                    Log.d("main", "onResponse: " + response.body());
                }
                else {
                    Log.d("main", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Actor>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "something went wrong...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
