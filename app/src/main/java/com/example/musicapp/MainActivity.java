package com.example.musicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TabItem rock, classic, pop;
    TabLayout tabLayout;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    ApiInterface apiInterface;
    Adapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        rock = findViewById(R.id.tab_item_rock);
        classic = findViewById(R.id.tab_item_classic);
        pop = findViewById(R.id.tab_item_pop);
        viewPager = findViewById(R.id.viewPager);
        recyclerView = findViewById(R.id.rv_rock);

        pageAdapter = new PageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pageAdapter);

        //new ExecuteNetworkCall("Rock", (TabLayout.OnTabSelectedListener) this).execute();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if (tab.getText().toString() == "Rock")
                    new ExecuteNetworkCall("rock", this).execute();
                else if (tab.getText().toString() == "Classic")
                    new ExecuteNetworkCall("classic", this).execute();
                else
                    new ExecuteNetworkCall("pop", this).execute();

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }


    class ExecuteNetworkCall extends AsyncTask<Void, Void, Void> {
        String type;
        Adapter adapter;


        public ExecuteNetworkCall(String string, TabLayout.OnTabSelectedListener context) {
            type = string;
            adapter = new Adapter((Context) context);
            recyclerView.setLayoutManager(new LinearLayoutManager((Context) context));
            recyclerView.setAdapter(adapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d("MusicApp", "doInBackground");

            String BASE_URL = "https://itunes.apple.com/";
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterface = retrofit.create(ApiInterface.class);
            apiInterface.getSongs("rock", "music", "song", "50")
                    .enqueue(new Callback<PojoResponse>() {
                        @Override
                        public void onResponse(Call<PojoResponse> call, Response<PojoResponse> response) {
                            if (response.isSuccessful())
                                adapter.setSongs(response.body().results);
                            Log.d("MusicApp", adapter.resultsPojos.toString());
                        }

                        @Override
                        public void onFailure(Call<PojoResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    });
            return null;
        }

        @Override
        protected void onPostExecute(Void s) {
            super.onPostExecute(s);

        }
    }
}
