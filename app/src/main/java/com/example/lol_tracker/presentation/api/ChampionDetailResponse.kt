package com.example.lol_tracker.presentation.api

data class ChampionDetailResponse(val name:String ,val types: List<ChampionSlot>)

data class ChampionSlot(val slot: Int, val type: ChampionType)

data class ChampionType(val name:String,val url:String)