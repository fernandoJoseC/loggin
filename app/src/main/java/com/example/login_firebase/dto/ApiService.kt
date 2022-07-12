package com.example.login_firebase.dto


import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/social")
    fun findsocial(): Call<List<Social>>

    @GET("api/mail")
    fun findmail(): Call<List<Mail>>

    @GET("api/code")
    fun findcode(): Call<List<Code>>
}
