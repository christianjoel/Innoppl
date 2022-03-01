package com.innoppl.roomdatabaseretrofit.Adapter

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
import android.content.Context
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository.InsertAsynTask
import com.innoppl.roomdatabaseretrofit.ViewModal.ActorViewModal
import com.innoppl.roomdatabaseretrofit.Adapter.ActorAdapter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

class ActorAdapter(private val context: Context, private var actorList: List<Actor>) :
    RecyclerView.Adapter<ActorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActorViewHolder {
        return ActorViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.each_roe, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ActorViewHolder, position: Int) {
        val actor = actorList[position]
        holder.id.text = "ID :" + actor.id
        holder.name.text = "NAME :" + actor.title
        holder.age.text = "Age " + actor.age
        Log.d("test", actor.image.toString() + "sfdsfsf")
        Glide.with(context)
            .load(actor.image)
            .into(holder.image)
        val url = GlideUrl(
            actor.image, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        )
        Glide.with(holder.image).load(
            url
        ).centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .skipMemoryCache(true)
            .into(holder.image)
    }

    fun getAllActors(actorList: List<Actor>) {
        this.actorList = actorList
    }

    override fun getItemCount(): Int {
        return actorList.size
    }

    class ActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var id: TextView
        var name: TextView
        var age: TextView
        var image: ImageView

        init {
            id = itemView.findViewById(R.id.id)
            name = itemView.findViewById(R.id.name)
            age = itemView.findViewById(R.id.age)
            image = itemView.findViewById(R.id.image)
        }
    }
}