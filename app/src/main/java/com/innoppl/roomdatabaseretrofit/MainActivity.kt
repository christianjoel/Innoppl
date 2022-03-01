package com.innoppl.roomdatabaseretrofit

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
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.innoppl.roomdatabaseretrofit.Network.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var actorViewModal: ActorViewModal? = null
    private var recyclerView: RecyclerView? = null
    private var actorList: List<Actor>? = null
    private var actorRespository: ActorRespository? = null
    private var actorAdapter: ActorAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerview)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.setLayoutManager(LinearLayoutManager(this))
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
        actorRespository = ActorRespository(application)
        actorList = ArrayList()
        actorAdapter = ActorAdapter(this, actorList as ArrayList<Actor>)
        actorViewModal = ViewModelProvider(this).get(
            ActorViewModal::class.java
        )
        networkRequest()
        actorViewModal!!.allActor!!.observe(this) { actorList ->
            recyclerView!!.adapter = actorAdapter
            actorAdapter!!.getAllActors(actorList as List<Actor>)
            Log.d("main", "onChanged: " + actorList!!.size + "sdfsdfsf")
        }
    }

    private fun networkRequest() {
        val retrofit = Retrofit()
        val call = retrofit.api.allActors

        call!!.enqueue(object : Callback<List<Actor?>?> {
            override fun onResponse(call: Call<List<Actor?>?>, response: Response<List<Actor?>?>) {
                if (response.isSuccessful) {
                    actorRespository!!.insert(response.body())
                }
                Log.d("main", "onResponse: " + response.body())
            }

            override fun onFailure(call: Call<List<Actor?>?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "something went wrong...", Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }
}