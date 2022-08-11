package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.appcompat.widget.SearchView
import com.example.login_firebase.dto.Send
import com.example.login_firebase.dto.Social

class CodeOptions : AppCompatActivity() {

    private lateinit var views: ActivityCodeOptionsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityCodeOptionsBinding.inflate(layoutInflater)
        setContentView(views.root)
        setSupportActionBar(views.toolbar)
        supportFragmentManager.beginTransaction().replace(views.fragmento.id, listadoRedesCode())
            .commit()
        accionesMenuBajo()
        accionesMenuArriba()
    }

    private fun accionesMenuArriba() {
        views.toolbar.setOnMenuItemClickListener { itemArriba ->
            when (itemArriba.itemId) {
                R.id.preferencias -> {
                    val intent = Intent(this@CodeOptions, MenuPreferences::class.java)
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
                    views.toolbar.title = "Code"
                    true
                }
                R.id.social -> {
                    supportFragmentManager.beginTransaction()
                        .replace(views.fragmento.id, listadoRedesSocial()).commit()
                    views.toolbar.title = "Social"
                    true
                }
                R.id.mail -> {
                    supportFragmentManager.beginTransaction()
                        .replace(views.fragmento.id, listadoRedesMail()).commit()
                    views.toolbar.title = "Mail"
                    true
                }
                else -> false
            }
        }
    }

    // BUSCADOR


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.principal, menu)
        enableFinder(menu.findItem(R.id.buscar))
        return true
    }

    private fun enableFinder(item: MenuItem) {
        val finder = item.actionView as SearchView
        finder.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searching(query.toString().trim())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searching(newText.toString().trim())
                return false
            }
        })
    }

    private fun searching(text: String) {
        text?.let { realText ->
            if (realText.length >= 1) {
                var enviar: Send = Send()
                enviar.nombre = realText
                WsClient.apiSocial()?.findCode(enviar)
                    ?.enqueue(object : Callback<List<Social>> {
                        override fun onResponse(
                            call: Call<List<Social>>, response: Response<List<Social>>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let { list ->
                                    fillFragments(list)
                                }
                            } else {

                            }
                        }

                        override fun onFailure(call: Call<List<Social>>, t: Throwable) {
                            Toast.makeText(this@CodeOptions, t.message, Toast.LENGTH_SHORT).show()
                        }
                    })
            } else {

            }
        }
    }

    private fun fillFragments(list: List<Social>) {
        supportFragmentManager.beginTransaction().replace(views.fragmento.id, busqueda(list))
            .commit()

    }


}