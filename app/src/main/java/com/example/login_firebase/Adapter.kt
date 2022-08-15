package com.example.login_firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.example.login_firebase.dto.Social
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.squareup.picasso.Picasso
import androidx.core.app.ActivityCompat.startActivityForResult as startActivityForResult

class Adapter(val list: List<Social>) : RecyclerView.Adapter<Adapter.MailHolder>() {

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

    override fun onBindViewHolder(holder: MailHolder, position: Int) {
        holder.viewsFragmentHolder.socialText.text = list[position].nombre
        Picasso.get().load(list[position].url).placeholder(R.mipmap.load).fit().centerInside()
            .into(holder.viewsFragmentHolder.imageSocial)
        holder.viewsFragmentHolder.buttonFragment.setOnClickListener {

            if (holder.viewsFragmentHolder.socialText.text == "Google") {
                val intent = Intent(holder.viewsFragmentHolder.root.context, Options::class.java)
                holder.viewsFragmentHolder.root.context.startActivity(intent)
//                ConexionGoogle()
            } else {
            }

        }
    }



    override fun getItemCount(): Int {
        return list.size
    }


}

