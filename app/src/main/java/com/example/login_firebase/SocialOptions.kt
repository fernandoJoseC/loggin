package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.databinding.ActivitySocialOptionsBinding
import com.example.login_firebase.dto.Social
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SocialOptions : AppCompatActivity() {

    private lateinit var views: ActivitySocialOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivitySocialOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        //initialConfiguration()
        //addListProducts()
        accionesMenuBajo()
        //accionesMenuArriba()
    }

//    private fun accionesMenuArriba(){
//        views.topAppBar.setOnMenuItemClickListener {
//            itemArriba ->
//            when(itemArriba.itemId){
//                R.id.preferencias -> {
//                    val intent = Intent(this@SocialOptions, menu_preferencias::class.java)
//                    startActivity(intent)
//                    finish()
//                    true
//                }
//                else->false
//            }
//        }
//
//    }

    private fun accionesMenuBajo() {
        views.navigation.setOnItemSelectedListener { itemBajo ->
            when(itemBajo.itemId){
                R.id.code -> {
                    val intent = Intent(this@SocialOptions, CodeOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.social -> {
                    val intent = Intent(this@SocialOptions, SocialOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.mail -> {
                    val intent = Intent(this@SocialOptions, MailOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else->false
            }
        }
    }
}