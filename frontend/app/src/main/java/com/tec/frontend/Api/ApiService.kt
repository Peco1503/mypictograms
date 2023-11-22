package com.tec.frontend.Api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @POST("/api/login")
    suspend fun login(@Body request: loginRequest): loginResponse

    @POST("/api/admins")
    suspend fun createAdmin(@Body request: registerRequest): Response<registerResponse>

    @POST("/api/parents")
    suspend fun createParent(@Body request: registerRequest): Response<registerResponse>

    @POST("/api/students")
    suspend fun insertalumno(@Body alumno: Alumno) : Response<Alumno>

    @GET("/api/students")
    suspend fun infoAlumno(@Query("therapistId") AdminID: Int) : List<Alumno>

    @GET("/api/students/{alumnoId}")
    suspend fun getEstudiante(@Path("alumnoId") alumnoId: Int): List<Alumno>

    @PUT("/api/students/{alumnoId}")
    suspend fun actualizarAlumno(@Path("alumnoId") alumnoId: Int, @Body alumno: Alumno): Response<Alumno>
}
