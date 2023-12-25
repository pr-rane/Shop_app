package com.example.shop_app.data.repo.user.responses

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class LoginResponse (
    @Json(name = "token")
    val token: String? = null,
    @Json(name = "message")
    val message: String? = null
)