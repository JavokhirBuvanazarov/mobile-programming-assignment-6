package com.example.sportsnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventsAdapter(private var eventsList: List<Event>) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val currentItem = eventsList[position]
        holder.textEventName.text = currentItem.name
        holder.textDescription.text = currentItem.description
        holder.textDate.text = currentItem.date
    }

    override fun getItemCount() = eventsList.size

    fun getEventsList(): List<Event> {
        return eventsList
    }

    fun submitList(newList: List<Event>) {
        eventsList = newList
        notifyDataSetChanged()
    }

    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textEventName: TextView = itemView.findViewById(R.id.textEventName)
        val textDescription: TextView = itemView.findViewById(R.id.textDescription)
        val textDate: TextView = itemView.findViewById(R.id.textDate)
    }
}

data class Event(
    val name: String,
    val description: String,
    val date: String
)