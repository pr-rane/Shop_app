package com.example.shop_app.data.models.responses


import com.example.shop_app.data.models.entities.Address
import com.example.shop_app.data.models.entities.Name
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class UserResponse(
    @Json(name = "address")
    val address: Address,
    @Json(name = "email")
    val email: String,
    @Json(name = "id")
    val id: Int,
    @Json(name = "name")
    val name: Name,
    @Json(name = "password")
    val password: String,
    @Json(name = "phone")
    val phone: String,
    @Json(name = "username")
    val username: String
)