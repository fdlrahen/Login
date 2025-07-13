package com.diva.userapi

import retrofit2.http.GET
import retrofit2.Call


interface APIInterface {
    @GET("users")
    suspend fun getUsers(): UserResponse
    @GET("products")
    suspend fun getProducts(): ProductResponse
}
