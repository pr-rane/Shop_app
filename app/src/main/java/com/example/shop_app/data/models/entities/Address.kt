package com.example.shop_app.data.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class Address(
    @Json(name = "city")
    val city: String,
    @Json(name = "geolocation")
    val geolocation: Geolocation,
    @Json(name = "number")
    val number: Int,
    @Json(name = "street")
    val street: String,
    @Json(name = "zipcode")
    val zipcode: String
)