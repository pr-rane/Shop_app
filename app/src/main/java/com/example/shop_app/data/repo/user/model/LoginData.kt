package com.example.shop_app.data.repo.user.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginData(
    @Json(name = "username")
    val username: String,
    @Json(name = "password")
    val password: String
)