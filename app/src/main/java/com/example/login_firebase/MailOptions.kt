package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.databinding.ActivitySocialOptionsBinding
import com.example.login_firebase.dto.Mail
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MailOptions : AppCompatActivity() {

    private lateinit var views: ActivityMailOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityMailOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        initialConfiguration()
        addListProducts()
        accionesMenuBajo()
        initialConfiguration()
        addListProducts()
    }

    private fun accionesMenuBajo() {
        views.navigation.setOnItemSelectedListener { itemBajo ->
            when(itemBajo.itemId){
                R.id.code -> {
                    val intent = Intent(this@MailOptions, ActivityCodeOptionsBinding::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.social -> {
                    val intent = Intent(this@MailOptions, ActivitySocialOptionsBinding::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.mail -> {
                    val intent = Intent(this@MailOptions, ActivityMailOptionsBinding::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else->false
            }
        }
    }

    private fun initialConfiguration() {
        views.listadoOpciones.layoutManager = LinearLayoutManager(this)
    }


    private fun addListProducts() {
        WsClient.apiSocial()?.findmail()?.enqueue(object : Callback<List<Mail>> {
            override fun onResponse(call: Call<List<Mail>>, response: Response<List<Mail>>) {
                if (response.isSuccessful) {
                    val list = response.body()!!
                    views.listadoOpciones.adapter = AdapterMail(list)
                } else {
                    Toast.makeText(
                        this@MailOptions,
                        android.R.string.httpErrorBadUrl,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Mail>>, t: Throwable) {
                Toast.makeText(
                    this@MailOptions,
                    android.R.string.httpErrorBadUrl,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}