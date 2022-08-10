package com.example.login_firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityFinderBinding

class Finder : AppCompatActivity() {

    private lateinit var views: ActivityFinderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityFinderBinding.inflate(layoutInflater)
        setContentView(views.root)
    }
}