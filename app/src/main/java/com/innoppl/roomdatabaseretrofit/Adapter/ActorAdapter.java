package com.innoppl.roomdatabaseretrofit.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.innoppl.roomdatabaseretrofit.Modal.Actor;
import com.innoppl.roomdatabaseretrofit.R;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {

private Context context;
private List<Actor> actorList;

    public ActorAdapter(Context context, List<Actor> actorList) {
        this.context =  context;
        this.actorList = actorList;
    }

    @NonNull
    @Override
    public ActorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ActorViewHolder(LayoutInflater.from(parent.getContext())
        .inflate(R.layout.each_roe,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActorViewHolder holder, int position) {
      Actor actor=actorList.get(position);
      holder.id.setText("Id "+actor.getId());
      holder.name.setText("Name "+actor.getTitle());
      holder.age.setText("Age "+actor.getAge());

        Log.d("test",actor.getImage().toString()+"sfdsfsf");

        Glide.with(context)
                .load(actor.getImage())
                .into(holder.image);

        GlideUrl url = new GlideUrl(actor.getImage(), new LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build());

                Glide.with(holder.image).load(
                        url

        ).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .into(holder.image);


    }

    public void getAllActors(List<Actor> actorList)
    {
        this.actorList=actorList;
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder{
      public TextView id,name,age;
      public ImageView image;

        public ActorViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.id);
            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            image=itemView.findViewById(R.id.image);
        }
    }
}