package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.dto.Mail
import com.example.login_firebase.dto.Send
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
        setSupportActionBar(views.toolbar)
//        initialConfiguration()
//        addListProducts()
//        accionesMenuBajo()
        accionesMenuArriba()
    }

    private fun accionesMenuArriba(){
        views.toolbar.setOnMenuItemClickListener {
                itemArriba ->
            when(itemArriba.itemId){
                R.id.preferencias -> {
                    val intent = Intent(this@MailOptions, menuPreferencias::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else->false
            }
        }

    }

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
            if (realText.length >= 3) {
                WsClient.apiSocial()?.findMail(Send.Builder().name(realText).build())
                    ?.enqueue(object : Callback<List<Mail>> {
                        override fun onResponse(
                            call: Call<List<Mail>>,
                            response: Response<List<Mail>>
                        ) {
                            if (response.isSuccessful) {
                                response.body()?.let { list -> fillFragments(list) }
                            } else {
                                views.fragmento.adapter = null
                            }
                        }

                        override fun onFailure(call: Call<List<Mail>>, t: Throwable) {
                            Toast.makeText(this@MailOptions, "Error, No data found", Toast.LENGTH_SHORT).show()
                        }

                    })
            } else {
                views.fragmento.adapter = null
            }

        }
    }

    private fun fillFragments(list: List<Mail>) {
        views.fragmento.layoutManager = LinearLayoutManager(this)
        views.fragmento.adapter = AdapterMail(list)
    }
}






