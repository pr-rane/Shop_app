package com.example.shop_app.data.models.entities


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class LoginData(
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String
)