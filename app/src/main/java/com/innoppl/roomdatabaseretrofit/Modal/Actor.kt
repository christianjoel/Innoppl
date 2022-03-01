package com.innoppl.roomdatabaseretrofit.Modal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "actor", indices = @Index(value = {"id"},unique = true))
public class Actor {
    @PrimaryKey(autoGenerate = true)
    private int albumId;

    @SerializedName("id")
    @ColumnInfo(name = "id")
    private int id;

    @SerializedName("title")
    @ColumnInfo(name = "title")
    private String title;

    @SerializedName("thumbnailUrl")
    @ColumnInfo(name = "thumbnailUrl")
    private String image;

    @SerializedName("age")
    @ColumnInfo(name = "age")
    private int age;


    public Actor(int id, String title, String image, int age) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "Actor{" +
                "albumId=" + albumId +
                ", id=" + id +
                ", name='" + title + '\'' +
                ", image='" + image + '\'' +
                ", age=" + age +
                '}';
    }



}
