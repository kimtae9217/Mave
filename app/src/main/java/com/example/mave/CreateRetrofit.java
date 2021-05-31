package com.example.mave;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateRetrofit {

    public static Retrofit createRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.173.164:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
