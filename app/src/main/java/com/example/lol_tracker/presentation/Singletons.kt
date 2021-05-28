package com.example.lol_tracker.presentation

import com.example.lol_tracker.presentation.api.ChampApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object {
        val champApi: ChampApi = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChampApi::class.java)
    }
}
