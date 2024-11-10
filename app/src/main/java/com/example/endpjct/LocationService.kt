package com.example.endpjct

import com.google.android.gms.awareness.snapshot.LocationResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {
    @GET("reverse")
    fun getLocation(
        @Query("key") apiKey: String,
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("format") format: String = "json"
    ): Call<LocationResponse>
}

