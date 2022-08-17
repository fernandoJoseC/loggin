package com.example.login_firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.login_firebase.databinding.ActivityMainBinding
import com.google.android.material.internal.NavigationMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

enum class ProviderType {
    BASIC,
    GOOGLE
}


class MainActivity : AppCompatActivity() {

    private lateinit var views: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)

        val email = intent.getStringExtra("email")
        val name = intent.getStringExtra("full_name")
        val url = intent.getStringExtra("photoUrl")

        views.name.text = name
        views.email.text = email

        //aqui va Glide
        Glide.with(this).load(url).centerCrop().into(views.photo);

        views.logoutBtn.setOnClickListener {
            /*val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            Firebase.auth.signOut()*/
            onBackPressed()
        }



    }


}