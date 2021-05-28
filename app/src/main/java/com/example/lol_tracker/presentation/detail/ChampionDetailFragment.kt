package com.example.lol_tracker.presentation.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.lol_tracker.R
import com.example.lol_tracker.presentation.Singletons
import com.example.lol_tracker.presentation.api.ChampionDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChampionDetailFragment : Fragment() {

    private lateinit var textViewName: TextView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.framgent_champion_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.navigateToChampionListFragment)
        }


        textViewName = view.findViewById(R.id.champion_detail_name)
        callApi();
    }
    private fun callApi(){
        Singletons.champApi.getChampionDetail("1").enqueue(object : Callback<ChampionDetailResponse>{
            override fun onFailure(call: Call<ChampionDetailResponse>, t: Throwable) {

            }
            override fun onResponse(call: Call<ChampionDetailResponse>, response: Response<ChampionDetailResponse>) {
                if(response.isSuccessful && response.body() != null){
                    textViewName.text = response.body()!!.name
                }
            }
        })
    }
}