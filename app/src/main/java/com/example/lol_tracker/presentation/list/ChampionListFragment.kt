package com.example.lol_tracker.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lol_tracker.R
import com.example.lol_tracker.presentation.api.ChampApi
import com.example.lol_tracker.presentation.api.ChampionResponse
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
    private val layoutManager = LinearLayoutManager(context)

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
            layoutManager = this@ChampionListFragment.layoutManager
            adapter = this@ChampionListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val champApi: ChampApi = retrofit.create(ChampApi::class.java)

        champApi.getChampionList().enqueue(object : Callback<ChampionResponse>{
            override fun onFailure(call: Call<ChampionResponse>, t: Throwable) {
                //TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<ChampionResponse>, response: Response<ChampionResponse>) {
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