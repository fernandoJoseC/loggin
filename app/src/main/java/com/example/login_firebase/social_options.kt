package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.databinding.ActivitySocialOptionsBinding

class social_options : AppCompatActivity() {

    private lateinit var views: ActivitySocialOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivitySocialOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        accionesMenuBajo()
    }

    private fun accionesMenuBajo() {
        views.navigation.setOnItemSelectedListener { itemBajo ->
            when(itemBajo.itemId){
                R.id.code -> {
                    val intent = Intent(this@social_options, ActivityCodeOptionsBinding::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.social -> {
                    val intent = Intent(this@social_options, ActivitySocialOptionsBinding::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.mail -> {
                    val intent = Intent(this@social_options, ActivityMailOptionsBinding::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else->false
            }
        }
    }
}