package com.example.lol_tracker.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChampionListViewModel : ViewModel() {
    val champList : LiveData<List<Champion>> = MutableLiveData()

    init{

    }
}