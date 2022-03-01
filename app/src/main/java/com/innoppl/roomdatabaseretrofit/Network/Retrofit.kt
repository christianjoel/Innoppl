package com.innoppl.roomdatabaseretrofit.Network

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import com.innoppl.roomdatabaseretrofit.Modal.Actor
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import androidx.room.ColumnInfo
import com.innoppl.roomdatabaseretrofit.Adapter.ActorAdapter.ActorViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.innoppl.roomdatabaseretrofit.R
import android.widget.TextView
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import androidx.room.Database
import com.innoppl.roomdatabaseretrofit.Dao.ActorDao
import com.innoppl.roomdatabaseretrofit.Database.ActorDatabase
import android.os.AsyncTask
import kotlin.jvm.Volatile
import com.innoppl.roomdatabaseretrofit.Database.ActorDatabase.PopulateAsynTask
import android.app.Application
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository.InsertAsynTask
import com.innoppl.roomdatabaseretrofit.ViewModal.ActorViewModal
import com.innoppl.roomdatabaseretrofit.Adapter.ActorAdapter
import android.os.Bundle
import android.widget.Toast
import retrofit2.Retrofit

class Retrofit {
    var retrofit = Retrofit.Builder()
        .baseUrl(Url.URL_DATA)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    var api = retrofit.create(
        Api::class.java
    )
}