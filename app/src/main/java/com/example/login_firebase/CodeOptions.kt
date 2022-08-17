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

    // MENU ARRRIBA
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

    //MENU BAJO

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

    //CREAMOS EL MENU
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.principal, menu)
        enableFinder(menu.findItem(R.id.buscar))
        return true
    }

    //CREAMOS LAS FUNCIONES PARA EL BUSCADOR

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
                val list = listOf(
                    OpcionesSignIn("Github", "https://cdn-icons-png.flaticon.com/512/733/733553.png"),
                    OpcionesSignIn("Google", "https://cdn-icons-png.flaticon.com/512/2702/2702602.png"),
                    OpcionesSignIn("Apple", "https://cdn-icons-png.flaticon.com/512/270/270781.png"),
                    OpcionesSignIn("Microsoft", "https://cdn-icons-png.flaticon.com/512/732/732221.png"),
                    OpcionesSignIn("Facebook", "https://cdn-icons-png.flaticon.com/512/733/733547.png"),
                    OpcionesSignIn("Twitter", "https://cdn-icons-png.flaticon.com/512/179/179342.png")
                )
                val filterList = list.filter { it.nombre == realText }
                fillFragments(filterList)

            } else {

            }
        }
    }

    //LLENAMOS LOS FRAGMENTOS CON LA LISTA

    private fun fillFragments(list: List<OpcionesSignIn>) {
        supportFragmentManager.beginTransaction().replace(views.fragmento.id, busqueda(list))
            .commit()

    }


}
