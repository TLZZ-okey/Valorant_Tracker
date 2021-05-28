package com.example.lol_tracker.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lol_tracker.R
import com.example.lol_tracker.presentation.Singletons
import com.example.lol_tracker.presentation.api.ChampApi
import com.example.lol_tracker.presentation.api.ChampionListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChampionListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = ChampionAdapter(listOf<Champion>(), ::onClickedChampion)

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
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

        Singletons.champApi.getChampionList().enqueue(object : Callback<ChampionListResponse>{
            override fun onFailure(call: Call<ChampionListResponse>, t: Throwable) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ChampionListResponse>, response: Response<ChampionListResponse>) {
                //TODO("Not yet implemented")
                if(response.isSuccessful && response.body() != null){
                    val championResponse = response.body()!!
                    adapter.updateList(championResponse.results)
                }
            }
        })

        /*val champList = arrayListOf<Champion>().apply{
            add(Champion("Ziggs"))
            add(Champion("Cassiopeia"))
            add(Champion("Ahri"))
            add(Champion("Jax"))
            add(Champion("Olaf"))
            add(Champion("Lux"))
        }*/

    }
    private fun onClickedChampion(champion: Champion){
            findNavController().navigate(R.id.navigateToChampionDetailFragment)
    }
}