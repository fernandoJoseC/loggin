package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityMainTwitterBinding

class MainActivityTwitter : AppCompatActivity() {

    private lateinit var views: ActivityMainTwitterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityMainTwitterBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)


        val twitterId = intent.getStringExtra("twitter_id")
        val twitterHandle = intent.getStringExtra("twitter_handle")
        val twitterName = intent.getStringExtra("twitter_name")
        val twitterEmail = intent.getStringExtra("twitter_email")
        val twitterProfilePicURL = intent.getStringExtra("twitter_profile_pic_url")
        val twitterAccessToken = intent.getStringExtra("twitter_access_token")

        views.email.text = twitterEmail
        views.name.text = twitterName



    }
}