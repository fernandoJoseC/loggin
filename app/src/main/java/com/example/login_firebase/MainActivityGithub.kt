package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityMainGithubBinding

class MainActivityGithub : AppCompatActivity() {

    private lateinit var views : ActivityMainGithubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityMainGithubBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        val githubId = intent.getStringExtra("github_id")
        val githubDisplayName = intent.getStringExtra("github_display_name")
        val githubEmail = intent.getStringExtra("github_email")
        val githubAvatarURL = intent.getStringExtra("github_avatar_url")
        val githubAccessToken = intent.getStringExtra("github_access_token")

        views.email.text = githubEmail
        views.name.text = githubDisplayName

    }
}