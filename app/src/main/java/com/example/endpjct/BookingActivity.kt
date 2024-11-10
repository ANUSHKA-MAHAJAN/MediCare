package com.example.endpjct

import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class BookingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        // Get the doctor’s name from the intent
        val doctorName = intent.getStringExtra("doctorName") ?: "Doctor"
        findViewById<TextView>(R.id.textViewDoctorName).text = doctorName

        // Set up DatePicker and TimePicker
        val datePicker = findViewById<DatePicker>(R.id.datePicker)
        val timePicker = findViewById<TimePicker>(R.id.timePicker)
        timePicker.setIs24HourView(true)
        timePicker.hour = 9

        findViewById<Button>(R.id.button_book).setOnClickListener {
            val selectedDate = "${datePicker.dayOfMonth}-${datePicker.month + 1}-${datePicker.year}"
            val selectedTime = "${timePicker.hour}:${timePicker.minute}"
            Toast.makeText(this, "Appointment booked with $doctorName on $selectedDate at $selectedTime", Toast.LENGTH_SHORT).show()
        }
    }
}

