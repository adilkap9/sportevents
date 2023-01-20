package com.example.sportevents.data

import com.example.sportevents.data.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {


    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val messageApi: FixtureService by lazy {
        retrofit.create(FixtureService::class.java)
    }
}