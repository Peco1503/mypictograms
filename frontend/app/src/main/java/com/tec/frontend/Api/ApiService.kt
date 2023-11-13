package com.tec.frontend.Api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/api/login")
    suspend fun login(@Body request: loginRequest): loginResponse

    @POST("/api/admins")
    suspend fun admins(@Body request: registerRequest): registerResponse
}
