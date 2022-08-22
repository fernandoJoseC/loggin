package com.example.login_firebase

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.login_firebase.databinding.ActivityGoogleBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException

class Google : AppCompatActivity() {

    private lateinit var views: ActivityGoogleBinding
    private lateinit var googleSignInClient: GoogleSignInClient


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        views = ActivityGoogleBinding.inflate(layoutInflater)
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
    }

    //Intent GoogleSignIn
    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 9001)
    }

    //[START onactivityresult] GOOGLE
    @SuppressLint("CommitPrefEdits")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == 9001) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!

                val intent = Intent(this, MainActivity::class.java).apply {
                    putExtra("full_name", account.displayName)
                    putExtra("email", account.email)
                    putExtra("photoUrl", account.photoUrl.toString())
                }

                //GUARDADO DE DATOS
                val prefs = getSharedPreferences(
                    getString(R.string.prefs_file),
                    Context.MODE_PRIVATE
                ).edit()
                prefs.putString("email", account.displayName)
                prefs.putString("idToken", account.idToken)
                prefs.apply()
                finishAffinity()

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
                        NotificationCompat.BigPictureStyle().bigPicture(image_uide)
                            .bigLargeIcon(image_google)
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

    //Creamos el canal notificaciones
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("WrongConstant")
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




}