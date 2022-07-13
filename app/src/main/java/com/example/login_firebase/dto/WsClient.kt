package com.example.login_firebase.dto

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class WsClient {
    companion object{
        private const val URL_social = "https://proyecto-pm.herokuapp.com/"
        private var retrofit: Retrofit? = null
        private fun retrofitClient(url: String): Retrofit? {
            retrofit = Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }

        fun apiSocial(): ApiService? {
            return retrofitClient(URL_social)?.create(ApiService::class.java)
        }

    }
}