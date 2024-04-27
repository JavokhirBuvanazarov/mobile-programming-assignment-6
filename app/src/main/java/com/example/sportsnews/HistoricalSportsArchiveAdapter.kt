package com.example.sportsnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoricalSportsArchiveAdapter(private var historyList: MutableList<History>) :
    RecyclerView.Adapter<HistoricalSportsArchiveAdapter.HistoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val currentItem = historyList[position]
        holder.textHistoryName.text = currentItem.name
        holder.textDescription.text = currentItem.description
        holder.textDate.text = currentItem.date
    }

    override fun getItemCount() = historyList.size

    fun getEventsList(): List<History> {
        return historyList
    }

    fun submitList(newList: MutableList<History>) {
        historyList = newList
        notifyDataSetChanged()
    }

    class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textHistoryName: TextView = itemView.findViewById(R.id.textHistoryName)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val textDate: TextView = itemView.findViewById(R.id.textDate)
    }
}

data class History(
    val name: String,
    val description: String,
    val date: String
)