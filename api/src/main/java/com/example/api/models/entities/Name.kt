package com.example.api.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "firstname")
    val firstname: String,
    @Json(name = "lastname")
    val lastname: String
)