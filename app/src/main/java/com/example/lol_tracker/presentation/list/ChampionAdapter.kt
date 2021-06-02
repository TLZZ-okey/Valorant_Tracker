package com.example.lol_tracker.presentation.list

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lol_tracker.R
import com.example.lol_tracker.presentation.Singletons
import com.example.lol_tracker.presentation.detail.ChampionDetailFragment

class ChampionAdapter(private var dataSet: List<Champion>, var listener: ((Champion) -> Unit)? = null) :
    RecyclerView.Adapter<ChampionAdapter.ViewHolder>() {
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    private lateinit var champion : Champion

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView
        val imageView: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.champion_name)
            imageView = view.findViewById(R.id.champion_img)

        }
    }

    fun updateList(list:List<Champion>){
        dataSet = list
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.champion_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        champion = dataSet[position]
        Singletons.position = position
        viewHolder.textView.text = champion.displayName
        viewHolder.itemView.setOnClickListener {
            Singletons.currentChampion = champion
            Log.e("ChampionAdapter", Singletons.currentChampion.displayName)
            listener?.invoke(champion)
        }

        Glide
                .with(viewHolder.itemView.context)
                .load(champion.displayIconSmall)
                .centerCrop()
                .into(viewHolder.imageView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}