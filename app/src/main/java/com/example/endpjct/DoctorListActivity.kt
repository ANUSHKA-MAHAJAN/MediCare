package com.example.endpjct

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.endpjct.LocationResponse
import com.example.endpjct.LocationService

class DoctorListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doctor_list)

        val specialty = intent.getStringExtra("specialty") ?: ""
        val latitude = intent.getDoubleExtra("latitude", 0.0)
        val longitude = intent.getDoubleExtra("longitude", 0.0)

        getDoctors(latitude, longitude, specialty)
    }

    private fun getDoctors(latitude: Double, longitude: Double, specialty: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://us1.locationiq.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(LocationService::class.java)
        val call = service.getLocation("pk.6368fae6dfa122d1240c1a52bbaafd40", latitude, longitude, "json")

        call.enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                if (response.isSuccessful) {
                    val location = response.body()?.address?.state ?: ""
                    val doctors = when (location) {
                        "Delhi" -> getDelhiDoctors(specialty)
                        "Punjab" -> getPunjabDoctors(specialty)
                        else -> listOf("No doctors available in this location")
                    }
                    displayDoctors(doctors)
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                Toast.makeText(this@DoctorListActivity, "Failed to retrieve location", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun getDelhiDoctors(specialty: String): List<String> {
        return listOf("Dr. Sharma - $specialty", "Dr. Gupta - $specialty")
    }

    private fun getPunjabDoctors(specialty: String): List<String> {
        return listOf("Dr. Kaur - $specialty", "Dr. Singh - $specialty")
    }

    private fun displayDoctors(doctors: List<String>) {
        val doctorListView = findViewById<ListView>(R.id.listView_doctors)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, doctors)
        doctorListView.adapter = adapter
    }
}

private fun Any.enqueue(callback: Callback<LocationResponse>) {

}




