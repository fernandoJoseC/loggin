package com.example.login_firebase.dto


import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @GET("api/social")
    fun findsocial(): Call<List<Social>>

    @GET("api/mail")
    fun findmail(): Call<List<Mail>>

    @GET("api/code")
    fun findcode(): Call<List<Code>>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(value = "api/social")
    fun findMail(
        @Body datasend: Send,
    ): Call<List<Mail>>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(value = "api/code")
    fun findCode(
        @Body datasend: Send,
    ): Call<List<Code>>

    @Headers("Content-Type: application/x-www-form-urlencoded")
    @POST(value = "api/social")
    fun findSocial(
        @Body datasend: Send,
    ): Call<List<Social>>
}
