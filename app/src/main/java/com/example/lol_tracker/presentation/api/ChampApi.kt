package com.example.lol_tracker.presentation.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface ChampApi {
    @GET("agents")
    fun getChampionList(): Call<ChampionListResponse>

    @GET("pokemon/{id}")
    fun getChampionDetail(@Path("id") id: String): Call<ChampionDetailResponse>
}