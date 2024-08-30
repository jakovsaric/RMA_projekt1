package com.jakovsaric.footballleaguesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    private val competitions: List<Competition>,
    private val clickListener: (Competition) -> Unit
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_1, parent, false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(competitions[position], clickListener)
    }

    override fun getItemCount(): Int = competitions.size

    class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val countryName: TextView = itemView.findViewById(android.R.id.text1)

        fun bind(competition: Competition, clickListener: (Competition) -> Unit) {
            countryName.text = competition.area.name
            itemView.setOnClickListener { clickListener(competition) }
        }
    }
}
