package com.example.shop_app.data.repo.user.request


import com.example.shop_app.data.repo.user.model.Address
import com.example.shop_app.data.repo.user.model.Name
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserRequest(
    @Json(name = "address")
    val address: Address,
    @Json(name = "email")
    val email: String,
    @Json(name = "name")
    val name: Name,
    @Json(name = "password")
    val password: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "username")
    val username: String
)