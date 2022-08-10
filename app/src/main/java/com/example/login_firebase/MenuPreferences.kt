package com.example.login_firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import com.example.login_firebase.databinding.ActivityMenuPreferenciasBinding

class MenuPreferences : AppCompatActivity() {
    private lateinit var views: ActivityMenuPreferenciasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityMenuPreferenciasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)

        views.modoOscuroSwitch.isChecked = true

        views.modoOscuroSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
            }else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }
        views.idioma.setOnClickListener {
            val intent = Intent(this@MenuPreferences, Idioma::class.java)
            startActivity(intent)
        }
        views.notificaciones.setOnClickListener {
            val intent = Intent(this@MenuPreferences, Notifications::class.java)
            startActivity(intent)
        }
        views.privacidad.setOnClickListener {
            val intent = Intent(this@MenuPreferences, Privacy::class.java)
            startActivity(intent)
        }
        views.seguridad.setOnClickListener {
            val intent = Intent(this@MenuPreferences, Security::class.java)
            startActivity(intent)
        }
        views.ayuda.setOnClickListener {
            val intent = Intent(this@MenuPreferences, Help::class.java)
            startActivity(intent)
        }
        views.informacion.setOnClickListener {
            val intent = Intent(this@MenuPreferences, Information::class.java)
            startActivity(intent)
        }
    }


}