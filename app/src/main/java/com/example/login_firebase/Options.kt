package com.example.login_firebase

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.SearchView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.login_firebase.databinding.ActivityCodeOptionsBinding
import com.example.login_firebase.databinding.ActivityMailOptionsBinding
import com.example.login_firebase.databinding.ActivityOptionsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider


class Options : AppCompatActivity() {

    private lateinit var views: ActivityOptionsBinding

    private lateinit var googleSignInClient: GoogleSignInClient

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityOptionsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(views.root)
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_id_token))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)

        views.googleBtn.setOnClickListener {
            signIn()
        }
        crearCanal()
        accionesMenuBajo()

    }

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.O)
    fun crearCanal() {
        val name = getString(R.string.app_name)
        val channelId = getString(R.string.user)
        val descriptionText = getString(R.string.dashboard)
        val importance = NotificationManager.IMPORTANCE_MAX

        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }

        val nm: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm.createNotificationChannel(channel)
    }



    private fun accionesMenuBajo() {
        views.navigation.setOnItemSelectedListener { itemBajo ->
            when (itemBajo.itemId) {

                R.id.code -> {
                    val intent = Intent(this@Options, CodeOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.social -> {
                    val intent = Intent(this@Options, SocialOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.mail -> {
                    val intent = Intent(this@Options, MailOptions::class.java)
                    startActivity(intent)
                    finish()
                    true
                }

                else -> false
            }
        }
    }

    // [START onactivityresult]
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            val any = try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(
                    "succes",
                    "datos:" + account.displayName + " " + account.email + " " + account.photoUrl + " " + account.idToken
                )
                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("full_name", account.displayName)
                    putExtra("email", account.email)
                    putExtra("photoUrl", account.photoUrl.toString())
                }

                val image_google = BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.image_google
                )
                val image_uide = BitmapFactory.decodeResource(
                    resources,
                    R.mipmap.uide_logo_notificacion
                )

                val channelId = getString(R.string.user)
                val notification = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.dashboard)
                    .setContentTitle("Bienvenido")
                    .setContentText(account.displayName)
                    .setSubText(account.email)
                    .setStyle(
                        NotificationCompat.BigTextStyle()
                            .bigText(account.displayName + ", has usado nuestra app para loggearte con Google")
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()

                val notification2 = NotificationCompat.Builder(this, channelId)
                    .setSmallIcon(R.drawable.dashboard)
                    .setContentTitle("Bienvenido")
                    .setContentText(account.displayName + ", has iniciado sesión desde nuestra app con Google")
                    .setSubText(account.email)
                    .setStyle(
                        NotificationCompat.BigPictureStyle().bigPicture(image_uide).bigLargeIcon(image_google)
                    )
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .build()

                with(NotificationManagerCompat.from(this)) {
                    notify(1, notification)
                }
                with(NotificationManagerCompat.from(this)) {
                    notify(2, notification2)
                }
                this.startActivity(intent)


            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("error", "Google sign in failed", e)
            }


        }
    }

    // [START signin]
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 9001)
    }


}






