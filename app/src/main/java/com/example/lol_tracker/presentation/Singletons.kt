package com.example.lol_tracker.presentation

import com.example.lol_tracker.presentation.api.ChampApi
import com.example.lol_tracker.presentation.list.Champion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object {
        val champApi: ChampApi = Retrofit.Builder()
            .baseUrl("https://valorant-api.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChampApi::class.java)
    }
}
