package com.example.musicapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity {

    //https://itunes.apple.com/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50

    public void initRetrofit(String artistName, String collectionName, String artworkUrl100, int trackPrice){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://itunes.apple.com/search?")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
