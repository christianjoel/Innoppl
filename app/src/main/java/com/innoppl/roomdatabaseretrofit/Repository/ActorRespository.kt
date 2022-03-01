package com.innoppl.roomdatabaseretrofit.Repository

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
import androidx.lifecycle.LiveData

class ActorRespository(application: Application?) {
    private val database: ActorDatabase? = ActorDatabase.Companion.getInstance(application)
    val allActors: LiveData<List<Actor?>?>? = database!!.actorDao().allActors
    fun insert(actorList: List<Actor?>?) {
        InsertAsynTask(database).execute(actorList)
    }

    internal class InsertAsynTask(actorDatabase: ActorDatabase?) :
        AsyncTask<List<Actor?>?, Void?, Void?>() {
        private val actorDao: ActorDao?
        override fun doInBackground(vararg lists: List<Actor?>?): Void? {
            actorDao!!.insert(lists[0])
            return null
        }

        init {
            actorDao = actorDatabase!!.actorDao()
        }
    }

}