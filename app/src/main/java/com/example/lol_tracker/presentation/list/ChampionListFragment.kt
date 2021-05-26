package com.example.lol_tracker.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lol_tracker.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ChampionListFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private val adapter = ChampionAdapter(listOf<Champion>())
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
        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_ChampionListFragment_to_FirstFragment)
        }

        recyclerView = view.findViewById(R.id.champion_recyclerview)
        recyclerView.apply {
            layoutManager = this@ChampionListFragment.layoutManager
            adapter = this@ChampionListFragment.adapter
        }

        val champList = arrayListOf<Champion>().apply{
            add(Champion("Ziggs"))
            add(Champion("Cassiopeia"))
            add(Champion("Ahri"))
            add(Champion("Jax"))
            add(Champion("Olaf"))
            add(Champion("Lux"))
        }

        adapter.updateList(champList)
    }
}