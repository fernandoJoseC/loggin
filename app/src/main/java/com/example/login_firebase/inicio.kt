package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.databinding.ActivityInicioBinding
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.databinding.ActivitySocialOptionsBinding

class inicio : AppCompatActivity() {

    private lateinit var views: ActivityInicioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityInicioBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        addListeners()
    }

    private fun addListeners() {
        views.codeInicio.setOnClickListener {
            goToCodeOptions()
        }
        views.socialInicio.setOnClickListener {
            goToSocialOptions()
        }
        views.mailInicio.setOnClickListener {
            goToMailOptions()
        }
    }

    private fun goToMailOptions() {
        val intent = Intent(this, ActivityMailOptionsBinding::class.java)
        startActivity(intent)
    }

    private fun goToSocialOptions() {
        val intent = Intent(this, ActivitySocialOptionsBinding::class.java)
        startActivity(intent)
    }

    private fun goToCodeOptions() {
        val intent = Intent(this, ActivityCodeOptionsBinding::class.java)
        startActivity(intent)
    }
}