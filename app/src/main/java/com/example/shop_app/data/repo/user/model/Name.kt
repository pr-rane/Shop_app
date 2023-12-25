package com.example.shop_app.data.repo.user.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "firstname")
    val firstname: String,
    @Json(name = "lastname")
    val lastname: String
)