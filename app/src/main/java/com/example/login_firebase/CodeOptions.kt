package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.databinding.ActivitySocialOptionsBinding
import com.example.login_firebase.dto.Code
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CodeOptions : AppCompatActivity() {

    private lateinit var views: ActivityCodeOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityCodeOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        initialConfiguration()
        addListProducts()
        accionesMenuBajo()
        accionesMenuArriba()
    }

    private fun accionesMenuArriba(){
        views.topAppBar.setOnMenuItemClickListener {
                itemArriba ->
            when(itemArriba.itemId){
                R.id.preferencias -> {
                    val intent = Intent(this@CodeOptions, menu_preferencias::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else->false
            }
        }

    }

    private fun accionesMenuBajo() {
        views.navigation.setOnItemSelectedListener { itemBajo ->
            when(itemBajo.itemId){
                R.id.code -> {
                    val intent = Intent(this@CodeOptions, CodeOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.social -> {
                    val intent = Intent(this@CodeOptions, SocialOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.mail -> {
                    val intent = Intent(this@CodeOptions, MailOptions::class.java)
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
        WsClient.apiSocial()?.findcode()?.enqueue(object : Callback<List<Code>> {
            override fun onResponse(call: Call<List<Code>>, response: Response<List<Code>>) {
                if (response.isSuccessful) {
                    val list = response.body()!!
                    views.listadoOpciones.adapter = AdapterCode(list)
                } else {
                    Toast.makeText(
                        this@CodeOptions,
                        android.R.string.httpErrorBadUrl,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<List<Code>>, t: Throwable) {
                Toast.makeText(
                    this@CodeOptions,
                    android.R.string.httpErrorBadUrl,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


}