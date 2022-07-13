package com.example.login_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.example.login_firebase.dto.Social
import com.squareup.picasso.Picasso

class AdapterSocial(val list: List<Social>) : RecyclerView.Adapter<AdapterSocial.SocialHolder>() {
    inner class SocialHolder(fragmentoView: View) : RecyclerView.ViewHolder(fragmentoView) {
        var viewsFragmentHolder: FragmentSiginOptionsBinding

        init {
            viewsFragmentHolder = FragmentSiginOptionsBinding.bind(fragmentoView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SocialHolder {
        return SocialHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sigin_options, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SocialHolder, position: Int) {
        holder.viewsFragmentHolder.socialText.text = list[position].nombre
        Picasso.get().load(list[position].url).fit().centerInside().into(holder.viewsFragmentHolder.imageSocial)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

