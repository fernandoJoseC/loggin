package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.dto.Code
import com.example.login_firebase.dto.Mail
import com.example.login_firebase.dto.Send
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
        setSupportActionBar(views.toolbar)
        supportFragmentManager.beginTransaction().replace(views.fragmento.id, listadoRedesCode())
            .commit()
        accionesMenuBajo()
        accionesMenuArriba()
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.principal, menu)
        (menu.findItem(R.id.buscar).actionView as SearchView).setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@CodeOptions, "aqui deben ir los fragmentos", Toast.LENGTH_SHORT)
                    .show()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@CodeOptions, "aqui deben ir los fragmentos", Toast.LENGTH_SHORT)
                    .show()
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }*/

//    private fun enableFinder(item: MenuItem) {
//        val finder = item.actionView as androidx.appcompat.widget.SearchView
//        finder.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                searching(query.toString().trim())
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                searching(newText.toString().trim())
//                return false
//            }
//        })
//    }

//    private fun searching(text: String) {
//        text?.let { realText ->
//            if (realText.length >= 3) {
//                WsClient.apiSocial()?.findMail(Send.Builder().name(realText).build())
//                    ?.enqueue(object : Callback<List<Mail>> {
//                        override fun onResponse(
//                            call: Call<List<Mail>>,
//                            response: Response<List<Mail>>
//                        ) {
//                            if (response.isSuccessful) {
//                                response.body()?.let { list -> fillFragments(list) }
//                            } else {
//                                views.listadoOpciones.adapter = null
//                            }
//                        }
//
//                        override fun onFailure(call: Call<List<Mail>>, t: Throwable) {
//                            Toast.makeText(this@MailOptions, "Error, No data found", Toast.LENGTH_SHORT).show()
//                        }
//
//                    })
//            } else {
//                views.fragmento.adapter = null
//            }
//
//        }
//    }


    private fun accionesMenuArriba() {
        views.toolbar.setOnMenuItemClickListener { itemArriba ->
            when (itemArriba.itemId) {
                R.id.preferencias -> {
                    val intent = Intent(this@CodeOptions, menu_preferencias::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

    }

    private fun accionesMenuBajo() {
        views.navigation.setOnItemSelectedListener { itemBajo ->
            when (itemBajo.itemId) {
                R.id.code -> {
                    supportFragmentManager.beginTransaction()
                        .replace(views.fragmento.id, listadoRedesCode()).commit()
                    true
                }
                R.id.social -> {
                    supportFragmentManager.beginTransaction()
                        .replace(views.fragmento.id, listadoRedesSocial()).commit()
                    true
                }
                R.id.mail -> {
                    supportFragmentManager.beginTransaction()
                        .replace(views.fragmento.id, listadoRedesMail()).commit()
                    true
                }
                else -> false
            }
        }
    }


}