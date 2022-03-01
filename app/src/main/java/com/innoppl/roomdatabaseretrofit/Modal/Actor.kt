package com.innoppl.roomdatabaseretrofit.Modal

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
import androidx.room.*

@Entity(tableName = "actor", indices = [Index(value = ["id"], unique = true)])
class Actor(
    @field:ColumnInfo(name = "id") @field:SerializedName("id") var id: Int,
    @field:ColumnInfo(
        name = "title"
    ) @field:SerializedName(
        "title"
    ) var title: String,
    @field:ColumnInfo(name = "thumbnailUrl") @field:SerializedName("thumbnailUrl") var image: String,
    @field:ColumnInfo(
        name = "age"
    ) @field:SerializedName(
        "age"
    ) var age: Int
) {
    @PrimaryKey(autoGenerate = true)
    var albumId = 0

    override fun toString(): String {
        return "Actor{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", name='" + title + '\'' +
                ", image='" + image + '\'' +
                ", age=" + age +
                '}'
    }
}