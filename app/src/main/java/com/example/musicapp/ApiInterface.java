package com.example.musicapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50
    @GET("search")
    Call<PojoResponse> getSongs(@Query("term") String type,
                                @Query("media") String media,
                                @Query("entity") String entity,
                                @Query("limit") String limit);
}
