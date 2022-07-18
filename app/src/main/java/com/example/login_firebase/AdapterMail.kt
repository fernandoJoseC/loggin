package com.example.login_firebase

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.example.login_firebase.dto.Mail
import com.squareup.picasso.Picasso

class AdapterMail(val list: List<Mail>) : RecyclerView.Adapter<AdapterMail.MailHolder>() {
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
        Picasso.get().load(list[position].url).fit().centerInside().into(holder.viewsFragmentHolder.imageSocial)
        holder.viewsFragmentHolder.buttonFragment.setOnClickListener {
            val intent = Intent(holder.viewsFragmentHolder.root.context, Options::class.java)
            holder.viewsFragmentHolder.root.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

