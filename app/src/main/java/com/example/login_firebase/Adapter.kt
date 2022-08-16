package com.example.login_firebase

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.example.login_firebase.dto.Social
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.squareup.picasso.Picasso
import androidx.core.app.ActivityCompat.startActivityForResult as startActivityForResult

class Adapter(val list: List<Social>, val activity: Activity) :
    RecyclerView.Adapter<Adapter.MailHolder>() {

    private lateinit var googleSignInClient: GoogleSignInClient


    inner class MailHolder(fragmentoView: View) : RecyclerView.ViewHolder(fragmentoView) {
        var viewsFragmentHolder: FragmentSiginOptionsBinding

        init {
            viewsFragmentHolder = FragmentSiginOptionsBinding.bind(fragmentoView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MailHolder {
        return MailHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sigin_options, parent, false)
        )
    }



    @SuppressLint("CommitPrefEdits")
     fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
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



    override fun onBindViewHolder(holder: MailHolder, position: Int) {
        holder.viewsFragmentHolder.socialText.text = list[position].nombre
        Picasso.get().load(list[position].url).placeholder(R.mipmap.load).fit().centerInside()
            .into(holder.viewsFragmentHolder.imageSocial)
        holder.viewsFragmentHolder.buttonFragment.setOnClickListener {

            if (holder.viewsFragmentHolder.socialText.text == "Google") {
                val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(holder.viewsFragmentHolder.root.context.getString(R.string.default_web_id_token))
                    .requestEmail()
                    .build()
                googleSignInClient = GoogleSignIn.getClient(activity, gso)
                signIn()




            } else if (holder.viewsFragmentHolder.socialText.text == "Facebook") {
                val intent = Intent(holder.viewsFragmentHolder.root.context, Options::class.java)
                holder.viewsFragmentHolder.root.context.startActivity(intent)
            }


        }
    }


    override fun getItemCount(): Int {
        return list.size
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        activity.startActivityForResult(signInIntent, 9001)
    }

}

