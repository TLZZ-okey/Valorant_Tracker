package com.example.lol_tracker.presentation.list

import java.io.Serializable

data class Champion(val displayName: String, val description: String, val abilities: List<Abilities>, val displayIconSmall: String, val fullPortrait: String) : Serializable

data class Abilities(val slot: String, val displayName: String, val description: String) : Serializable