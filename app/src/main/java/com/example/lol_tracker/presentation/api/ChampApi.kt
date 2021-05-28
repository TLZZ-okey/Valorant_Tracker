package com.example.lol_tracker.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ChampApi {
    @GET("pokemon")
    fun getChampionList(): Call<ChampionResponse>
}