package com.example.lol_tracker.presentation.list

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lol_tracker.R
import com.example.lol_tracker.presentation.Singletons
import com.example.lol_tracker.presentation.api.ChampApi
import com.example.lol_tracker.presentation.api.ChampionListResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChampionListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = ChampionAdapter(listOf<Champion>(), ::onClickedChampion)
    private var sharedPref = activity?.getSharedPreferences("app", Context.MODE_PRIVATE)
    private val g = Gson()



    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //Log.e("Champion", Singletons.currentChampion.displayName)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_champion_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.champion_recyclerview)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ChampionListFragment.adapter
        }
        val list = getListFromCache()
        if(list == null){
            callApi()
        } else{
            showList(list)
        }
    }

    private fun getListFromCache() : List<Champion>? {
        val json : String? = sharedPref?.getString("jsonChampionList", null)
        if(json == null){
            return null
        }else{
            val type: Type = object : TypeToken<List<Champion>>() {}.type
            return g.fromJson(json, type)
        }
    }

    private fun saveListIntoCache(champList: List<Champion>) {
        val json : String = g.toJson(champList)
        sharedPref
                ?.edit()
               ?.putString("jsonChampionList", json)
               ?.apply()
    }

    private fun showList(champList: List<Champion>){
        adapter.updateList(champList)
    }

    private fun callApi() {
        val champApi: ChampApi = Retrofit.Builder()
                .baseUrl("https://valorant-api.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ChampApi::class.java)

        champApi.getChampionList().enqueue(object : Callback<ChampionListResponse> {
            override fun onFailure(call: Call<ChampionListResponse>, t: Throwable) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(
                    call: Call<ChampionListResponse>,
                    response: Response<ChampionListResponse>
            ) {
                //TODO("Not yet implemented")
                if (response.isSuccessful && response.body() != null) {
                    val championResponse = response.body()!!
                    saveListIntoCache(championResponse.data)
                    Log.e("Cache", "List saved")
                    adapter.updateList(championResponse.data)
                    Singletons.champList = championResponse.data
                    /*lateinit var champ : Champion
                    var i : Int = 0;
                    for(champ in Singletons.champList){
                        Log.e("List champion", champ.displayName)
                        Log.e("id", i.toString())
                        i++
                    }*/
                }
            }
        })
    }

    private fun onClickedChampion(champion: Champion){
        findNavController().navigate(
                R.id.navigateToChampionDetailFragment, bundleOf(
                "current_champion" to champion
        )
        )
    }
}