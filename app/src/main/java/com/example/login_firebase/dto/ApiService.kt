package com.example.login_firebase.dto


import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("api/social")
    fun findsocial(): Call<List<Social>>

    @GET("api/mail")
    fun findmail(): Call<List<Social>>

    @GET("api/code")
    fun findcode(): Call<List<Social>>


    @POST("api/todo")
    fun findCode(
        @Body envio: Send
    ): Call<List<Social>>
}
