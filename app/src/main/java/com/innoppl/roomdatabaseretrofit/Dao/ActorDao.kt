package com.innoppl.roomdatabaseretrofit.Dao

import com.innoppl.roomdatabaseretrofit.Modal.Actor
import com.google.gson.annotations.SerializedName
import com.innoppl.roomdatabaseretrofit.Adapter.ActorAdapter.ActorViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import com.innoppl.roomdatabaseretrofit.R
import android.widget.TextView
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
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
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(actorList: List<Actor?>?)

    @get:Query("SELECT * FROM actor")
    val allActors: LiveData<List<Actor?>?>?

    @Query("DELETE FROM actor")
    fun deleteAll()
}