package com.example.sportsnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SportAdapter(private val sports: MutableList<Sport>) : RecyclerView.Adapter<SportAdapter.SportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sports, parent, false)
        return SportViewHolder(view)
    }

    override fun onBindViewHolder(holder: SportViewHolder, position: Int) {
        val sport = sports[position]
        holder.bind(sport)
    }

    override fun getItemCount(): Int {
        return sports.size
    }


    fun addItem(newSport: Sport) {
        sports.add(newSport)
        notifyItemInserted(sports.size - 1)
    }

    inner class SportViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textSportType: TextView = itemView.findViewById(R.id.text_sport_type)
        private val textSportName: TextView = itemView.findViewById(R.id.text_sport_name)
        private val textInstruction: TextView = itemView.findViewById(R.id.text_instruction)

        fun bind(sport: Sport) {
            textSportType.text = sport.type
            textSportName.text = sport.name
            textInstruction.text = sport.instruction
        }
    }
}

data class Sport(val type: String, val name: String, val instruction: String)