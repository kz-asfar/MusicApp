package com.example.musicapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView tvTitle, tvArtist, tvPrice;

    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.iv_track_thumb);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvArtist = itemView.findViewById(R.id.tv_artist);
        tvPrice = itemView.findViewById(R.id.tv_price);
    }
}
