package com.example.sportsnews

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SportsFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var sportAdapter: SportAdapter
    private lateinit var fabAdd: FloatingActionButton

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sports    , container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

        fabAdd = view.findViewById(R.id.fabAdd)
        fabAdd.setOnClickListener {
            showAddSportDialog()
        }

        val sports = mutableListOf(
            Sport("Measure", "Formula 1", "In Measure sports, competitors aim to achieve the highest measurable performance. Formula 1 racing exemplifies this category with its focus on achieving the fastest times over specified laps, where precision in speed and pit-stop strategy plays a critical role."),
            Sport("Precision", "Archery", "Precision sports demand high levels of skill and accuracy. Archery challenges participants to hit a series of targets at varying distances, where precision and calmness under pressure are key."),
            Sport("Spectacle", "Figure Skating", "Spectacle sports prioritize artistic expression alongside athletic skill. Figure skating combines jumps, spins, and dance moves, scored on both technical execution and artistic impression."),
            Sport("Combat", "Mixed Martial Arts", "Combat sports encompass various fighting styles in direct competition. Mixed Martial Arts (MMA) involves techniques from boxing, wrestling, judo, and more, requiring comprehensive fighting skills and strategy."),
            Sport("Play", "Soccer", "Soccer epitomizes Play sports through its combination of complex rules, team strategy, and individual skill. Teams must maneuver a ball into the opponent’s goal using coordinated play, with positions and tactics that vary widely.")
        )


        sportAdapter = SportAdapter(sports)
        recyclerView.adapter = sportAdapter

        return view
    }

    private fun showAddSportDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_sport, null)

        val editTextType = dialogView.findViewById<EditText>(R.id.editTextType)
        val editTextName = dialogView.findViewById<EditText>(R.id.editTextName)
        val editTextInstruction = dialogView.findViewById<EditText>(R.id.editTextInstruction)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Sport")
            .setPositiveButton("Add") { dialog, _ ->
                val type = editTextType.text.toString().trim()
                val name = editTextName.text.toString().trim()
                val instruction = editTextInstruction.text.toString().trim()

                if (type.isNotEmpty() && name.isNotEmpty() && instruction.isNotEmpty()) {
                    val newSport = Sport(type, name, instruction)
                    addSport(newSport)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addSport(newSport: Sport) {
        sportAdapter.addItem(newSport)
    }
}