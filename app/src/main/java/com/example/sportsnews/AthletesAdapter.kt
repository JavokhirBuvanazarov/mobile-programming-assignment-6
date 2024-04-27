package com.example.sportsnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AthletesAdapter(private var athletesList: List<Athlete>) : RecyclerView.Adapter<AthletesAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textAthleteName: TextView = itemView.findViewById(R.id.textAthleteName)
        val textSportName: TextView = itemView.findViewById(R.id.textSportName)
        val textCountry: TextView = itemView.findViewById(R.id.textCountry)
        val textBestPerformance: TextView = itemView.findViewById(R.id.textBestPerformance)
        val textMedal: TextView = itemView.findViewById(R.id.textMedal)
        val textFacts: TextView = itemView.findViewById(R.id.textFacts)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_athlete, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentAthlete = athletesList[position]
        holder.textAthleteName.text = currentAthlete.name
        holder.textSportName.text = currentAthlete.sport
        holder.textCountry.text = currentAthlete.country
        holder.textBestPerformance.text = currentAthlete.bestPerformance
        holder.textMedal.text = currentAthlete.medal
        holder.textFacts.text = currentAthlete.facts
    }

    override fun getItemCount() = athletesList.size

    fun getAthletesList(): List<Athlete> {
        return athletesList
    }

    fun submitList(newList: List<Athlete>) {
        athletesList = newList
        notifyDataSetChanged()
    }
}

data class Athlete(
    val name: String,
    val sport: String,
    val country: String,
    val bestPerformance: String,
    val medal: String,
    val facts: String
)
