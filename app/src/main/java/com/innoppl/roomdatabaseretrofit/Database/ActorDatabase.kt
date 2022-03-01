package com.innoppl.roomdatabaseretrofit.Database

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
import android.content.Context
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository
import com.innoppl.roomdatabaseretrofit.Repository.ActorRespository.InsertAsynTask
import com.innoppl.roomdatabaseretrofit.ViewModal.ActorViewModal
import com.innoppl.roomdatabaseretrofit.Adapter.ActorAdapter
import android.os.Bundle
import android.widget.Toast
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [Actor::class], version = 1)
abstract class ActorDatabase : RoomDatabase() {
    abstract fun actorDao(): ActorDao
    internal class PopulateAsynTask(actorDatabase: ActorDatabase?) :
        AsyncTask<Void?, Void?, Void?>() {
        private val actorDao: ActorDao
        protected override fun doInBackground(vararg p0: Void?): Void? {
            actorDao.deleteAll()
            return null
        }

        init {
            actorDao = actorDatabase!!.actorDao()
        }
    }

    companion object {
        private const val DATABASE_NAME = "ActorDatabase"

        @Volatile
        private var INSTANCE: ActorDatabase? = null
        fun getInstance(context: Context?): ActorDatabase? {
            if (INSTANCE == null) {
                synchronized(ActorDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context!!, ActorDatabase::class.java,
                            DATABASE_NAME
                        )
                            .addCallback(callback)
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

        var callback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateAsynTask(INSTANCE)
            }
        }
    }
}