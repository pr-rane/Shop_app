package com.example.api.models.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginResponse {

    @Json(name = "token")
    val token: String? = null
    @Json(name = "message")
    val message: String? = null
}