package com.innoppl.roomdatabaseretrofit.Network;

import com.innoppl.roomdatabaseretrofit.Modal.Actor;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("photos")
    Call<List<Actor>> getAllActors();
}
