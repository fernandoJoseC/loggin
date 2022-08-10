package com.example.login_firebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.example.login_firebase.dto.Code
import com.example.login_firebase.dto.Social
import com.squareup.picasso.Picasso

class AdapterCode(val list: List<Code>) : RecyclerView.Adapter<AdapterCode.CodeHolder>() {
    inner class CodeHolder(fragmentoView: View) : RecyclerView.ViewHolder(fragmentoView) {
        var viewsFragmentHolder: FragmentSiginOptionsBinding

        init {
            viewsFragmentHolder = FragmentSiginOptionsBinding.bind(fragmentoView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeHolder {
        return CodeHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sigin_options, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CodeHolder, position: Int) {
        holder.viewsFragmentHolder.socialText.text = list[position].nombre
        Picasso.get().load(list[position].url).placeholder(R.mipmap.load).fit().centerInside().into(holder.viewsFragmentHolder.imageSocial)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

