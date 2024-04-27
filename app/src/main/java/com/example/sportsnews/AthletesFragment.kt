package com.example.sportsnews

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class AthletesFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AthletesAdapter
    private lateinit var fabAddAthlete: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_athletes, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewAthletes)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddAthlete = view.findViewById(R.id.fabAddAthlete)
        fabAddAthlete.setOnClickListener {
            showAddAthleteDialog()
        }

        val athletesList = mutableListOf<Athlete>(
            Athlete("Simone Biles", "Gymnastics", "United States", "Gold in Women's Individual All-Around Rio 2016", "Gold", "Simone has a total of 30 Olympic and World Championship medals, making her one of the most decorated gymnasts in history."),
            Athlete("Lionel Messi", "Football", "Argentina", "Winner of seven Ballon d'Or awards", "None", "Messi has won numerous trophies with Barcelona, including four Champions League titles, and led Argentina to victory in the 2021 Copa America.")
        )

        adapter = AthletesAdapter(athletesList)
        recyclerView.adapter = adapter

        return view
    }

    private fun showAddAthleteDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_athlete, null)

        val editTextAthleteName = dialogView.findViewById<EditText>(R.id.editTextAthleteName)
        val editTextSportName = dialogView.findViewById<EditText>(R.id.editTextSportName)
        val editTextCountry = dialogView.findViewById<EditText>(R.id.editTextCountry)
        val editTextBestPerformance = dialogView.findViewById<EditText>(R.id.editTextBestPerformance)
        val editTextMedal = dialogView.findViewById<EditText>(R.id.editTextMedal)
        val editTextFacts = dialogView.findViewById<EditText>(R.id.editTextFacts)

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add Athlete")
            .setPositiveButton("Add") { dialog, _ ->
                val athleteName = editTextAthleteName.text.toString().trim()
                val sportName = editTextSportName.text.toString().trim()
                val country = editTextCountry.text.toString().trim()
                val bestPerformance = editTextBestPerformance.text.toString().trim()
                val medal = editTextMedal.text.toString().trim()
                val facts = editTextFacts.text.toString().trim()

                if (athleteName.isNotEmpty() && sportName.isNotEmpty() && country.isNotEmpty() &&
                    bestPerformance.isNotEmpty() && medal.isNotEmpty() && facts.isNotEmpty()) {
                    val newAthlete = Athlete(athleteName, sportName, country, bestPerformance, medal, facts)
                    addAthlete(newAthlete)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addAthlete(newAthlete: Athlete) {
        val athletesList = adapter.getAthletesList().toMutableList()
        athletesList.add(newAthlete)
        adapter.submitList(athletesList)
    }
}