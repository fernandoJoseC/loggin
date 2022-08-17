package com.example.login_firebase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.squareup.picasso.Picasso

class Adapter(val list: List<OpcionesSignIn>, val framentActivity: FragmentActivity) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var googleSignInClient: GoogleSignInClient



    private val despuesDeLogearse =
        framentActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->


            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            if (result.resultCode == 9001) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!

                    //GUARDADO DE DATOS

                    framentActivity.getSharedPreferences(
                        framentActivity.resources.getString(R.string.prefs_file),
                        Context.MODE_PRIVATE
                    ).edit().putString("email", account.displayName).putString("idToken", account.idToken).apply()

                    /*
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
                    */
                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("error", "Google sign in failed", e)
                }
            }
        }

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(framentActivity.resources.getString(R.string.default_web_id_token))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(framentActivity, gso)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var views: FragmentSiginOptionsBinding
        init {
            views = FragmentSiginOptionsBinding.bind(view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_sigin_options, parent, false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.views.socialText.text = list[position].nombre
        holder.views.buttonFragment.setOnClickListener {
            despuesDeLogearse.launch(googleSignInClient.signInIntent)
        }



    }

    override fun getItemCount() = list.size


}

