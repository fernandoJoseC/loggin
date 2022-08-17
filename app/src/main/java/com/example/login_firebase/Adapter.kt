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
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.contentValuesOf
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.squareup.picasso.Picasso

class Adapter(
    val list: List<OpcionesSignIn>,
    val fragmentActivity: FragmentActivity
) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {


    var googleSignInClient: GoogleSignInClient

    init {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(fragmentActivity.resources.getString(R.string.default_web_id_token))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(fragmentActivity, gso)
    }



    private val despuesLogearse =
        fragmentActivity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!


                    //GUARDADO DE DATOS
                    fragmentActivity.getSharedPreferences(
                        fragmentActivity.resources.getString(R.string.prefs_file),
                        Context.MODE_PRIVATE
                    ).edit().putString("email", account.displayName)
                        .putString("idToken", account.idToken).apply()
                    fragmentActivity.startActivity(
                        Intent(
                            fragmentActivity,
                            MainActivity::class.java
                        )
                    )
                    fragmentActivity.finishAffinity()

                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Log.w("error", "Google sign in failed", e)
                }
            }
        }


    class ViewHolder(fragmentView: View) : RecyclerView.ViewHolder(fragmentView) {
        val views: FragmentSiginOptionsBinding

        init {
            views = FragmentSiginOptionsBinding.bind(fragmentView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sigin_options, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.views.socialText.text = list[position].nombre
        Picasso.get().load(list[position].url).placeholder(R.mipmap.load).fit().centerInside()
            .into(holder.views.imageSocial)

        holder.views.buttonFragment.setOnClickListener {
            when (holder.views.socialText.text) {
                "Google" -> {

                    despuesLogearse.launch(googleSignInClient.signInIntent)


                }
                "Facebook" -> {}
                "Twitter" -> {}
                "Github" -> {}
                "Microsoft" -> {}
                else -> {}
            }
        }


    }

    override fun getItemCount() = list.size


}

