package com.example.lol_tracker.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.example.lol_tracker.presentation.list.Champion
import com.example.lol_tracker.presentation.list.ChampionAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ChampionDetailFragment : Fragment() {
    private lateinit var textViewName: TextView
    private lateinit var textViewDescription: TextView

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
        val currentChampion : Champion = arguments?.getSerializable("current_champion") as Champion
        Log.e("Champion", currentChampion.displayName)
        textViewName = view.findViewById(R.id.champion_detail_name)
        textViewDescription = view.findViewById(R.id.champion_detail_description)

        textViewName.text = currentChampion.displayName
        textViewDescription.text = currentChampion.description
    }
}
