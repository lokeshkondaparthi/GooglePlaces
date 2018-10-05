package com.procon.googlemaps.network;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.procon.googlemaps.IAppConstants.BASE_URL;

public class RetroHelper {
    public static Retrofit getAdapter(Context ctx, String serverUrl) {
        Gson gson = new Gson();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL + serverUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}