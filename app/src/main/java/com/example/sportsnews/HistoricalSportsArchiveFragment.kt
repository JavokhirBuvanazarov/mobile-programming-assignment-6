package com.example.sportsnews

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class HistoricalSportsArchiveFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HistoricalSportsArchiveAdapter
    private lateinit var fabAddHistory: FloatingActionButton
    private var btnSelectDate: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_historical_sports_archive, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewHistory)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        fabAddHistory = view.findViewById(R.id.fabAddHistory)
        fabAddHistory.setOnClickListener {
            showAddHistoryDialog()
        }

        val historyList = mutableListOf<History>(
            History("" +
                    "World War II", "A global war that lasted from 1939 to 1945.", "1939-09-01"),
            History("September 11 Attacks", "Terrorist attacks on the United States on September 11, 2001.", "2001-09-11"),
        )

        adapter = HistoricalSportsArchiveAdapter(historyList)
        recyclerView.adapter = adapter

        return view
    }

    private fun showAddHistoryDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_history, null)

        val editTextHistoryName = dialogView.findViewById<EditText>(R.id.editTextHistoryName)
        val editTextDescription = dialogView.findViewById<EditText>(R.id.editTextDescription)
        btnSelectDate = dialogView.findViewById<Button>(R.id.btnSelectDate)

        btnSelectDate?.setOnClickListener {
            showDatePicker()
        }

        val dialogBuilder = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setTitle("Add History")
            .setPositiveButton("Add") { dialog, _ ->
                val historyName = editTextHistoryName.text.toString().trim()
                val description = editTextDescription.text.toString().trim()
                val currentDate = getCurrentDate()

                if (historyName.isNotEmpty()) {
                    val newHistory = History(historyName, description, currentDate)
                    addHistory(newHistory)
                }

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun addHistory(newHistory: History) {
        val historysList = adapter.getEventsList().toMutableList()
        historysList.add(newHistory)
        adapter.submitList(historysList)
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$dayOfMonth"
    }

    private fun showDatePicker() {
        btnSelectDate?.let {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedYear-${selectedMonth + 1}-$selectedDay"
                // Update the button text with selected date
                btnSelectDate?.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }
    }
}