package com.example.login_firebase

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.example.login_firebase.databinding.ActivityOptionsBinding

class Options : AppCompatActivity() {

    private lateinit var views: ActivityOptionsBinding


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        views.googleBtn.setOnClickListener {
            signInGoogle()
        }

        views.facebobkBtn.setOnClickListener {
            signInFacebook()
        }

        views.githubBtn.setOnClickListener {
            signInGithub()
        }

        views.twitterBtn.setOnClickListener {
            signInTwitter()
        }

    }

    private fun signInTwitter() {
        startActivity(Intent(this, Twitter::class.java))
    }


    // [START signin]

    private fun signInFacebook() {
        startActivity(Intent(this, Facebook::class.java))
    }


    // Google
    private fun signInGoogle() {
        startActivity(Intent(this, Google::class.java))
    }

    // Github
    private fun signInGithub() {
        startActivity(Intent(this, Github::class.java))
    }

}











