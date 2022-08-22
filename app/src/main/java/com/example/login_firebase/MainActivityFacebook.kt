package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityMainFacebookBinding
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso

class MainActivityFacebook : AppCompatActivity() {
    private lateinit var views: ActivityMainFacebookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityMainFacebookBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        val facebookId = intent.getStringExtra("facebook_id")
        val facebookFirstName = intent.getStringExtra("facebook_first_name")
        val facebookMiddleName = intent.getStringExtra("facebook_middle_name")
        val facebookLastName = intent.getStringExtra("facebook_last_name")
        val facebookName = intent.getStringExtra("facebook_name")
        val facebookPicture = intent.getStringExtra("facebook_picture")
        val facebookEmail = intent.getStringExtra("facebook_email")
        val facebookAccessToken = intent.getStringExtra("facebook_access_token")


        views.name.text = facebookName
        views.email.text = facebookEmail
        //views.photo.text = facebookPicture
    }
}