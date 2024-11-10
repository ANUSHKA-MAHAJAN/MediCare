package com.example.endpjct

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DoctorSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_selection)

        // Example doctors based on selected specialty and location (Delhi, Punjab)
        val doctors = listOf(
            "Dr. Arya - Cardiologist",
            "Dr. Singh - General Physician",
            "Dr. Mehta - Dentist"
        )

        val listView = findViewById<ListView>(R.id.listView_doctors)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, doctors)
        listView.adapter = adapter

        // Handle doctor selection and navigate to BookingActivity
        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedDoctor = doctors[position]
            val intent = Intent(this, BookingActivity::class.java)
            intent.putExtra("doctorName", selectedDoctor)
            startActivity(intent)
        }
    }
}

