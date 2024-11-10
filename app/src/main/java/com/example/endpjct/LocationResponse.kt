package com.example.endpjct

data class LocationResponse(
    val address: Address
)

data class Address(
    val city: String,
    val state: String,
    val country: String
)

