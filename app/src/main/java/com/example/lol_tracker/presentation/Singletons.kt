package com.example.lol_tracker.presentation

import com.example.lol_tracker.presentation.api.ChampApi
import com.example.lol_tracker.presentation.list.Champion
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Singletons {
    companion object {
        lateinit var currentChampion : Champion
        lateinit var champList : List<Champion>
        var position : Int = 0
    }
}
