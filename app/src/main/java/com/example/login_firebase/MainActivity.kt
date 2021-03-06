package com.example.login_firebase

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.example.login_firebase.databinding.ActivityMainBinding
import com.google.android.material.internal.NavigationMenu
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

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

        val nName = views.name
        val nEmail = views.email
        val mProfileImage = views.photo

        nName.text = name
        nEmail.text = email

        Glide.with(this).load(url).centerCrop().into(mProfileImage);
        //aqui va picasso

        //guardado de datos
        val prefs =
            getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.apply()

        views.logoutBtn.setOnClickListener {
            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()
            prefs.commit()

            Firebase.auth.signOut()
            onBackPressed()
        }



    }





}