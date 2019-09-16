package com.example.musicapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class Adapter extends RecyclerView.Adapter<CustomViewHolder> {
    LayoutInflater inflater;
    List<ResultsPojo> resultsPojos;

    public Adapter(Context context){

        resultsPojos = new ArrayList<ResultsPojo>();
        inflater = new LayoutInflater(context) {

            @Override
            public LayoutInflater cloneInContext(Context newContext) {
                return null;
            }
        };
    }



    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_layout, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        Picasso.get().load(resultsPojos.get(position).artworkUrl100).into(holder.imageView);
        holder.tvTitle.setText(resultsPojos.get(position).collectionName);
        holder.tvArtist.setText(resultsPojos.get(position).artistName);
        holder.tvPrice.setText("$" + resultsPojos.get(position).trackPrice);
    }

    @Override
    public int getItemCount() {
        return resultsPojos.size();
    }

    public void setSongs(List<ResultsPojo> resultsPojos){
        this.resultsPojos = resultsPojos;
        notifyDataSetChanged();
    }
}
