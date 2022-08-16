package com.example.login_firebase

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login_firebase.databinding.FragmentSiginOptionsBinding
import com.squareup.picasso.Picasso

class Adapter(val list: List<OpcionesSignIn>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(private val binding: FragmentSiginOptionsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(red: OpcionesSignIn){
            binding.socialText.text = red.nombre
            Picasso.get().load(red.url).placeholder(R.mipmap.load).fit().centerInside().into(binding.imageSocial)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FragmentSiginOptionsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*holder.viewsFragmentHolder.socialText.text = list[position].nombre
        Picasso.get().load(list[position].url).placeholder(R.mipmap.load).fit().centerInside()
            .into(holder.viewsFragmentHolder.imageSocial)
        holder.viewsFragmentHolder.buttonFragment.setOnClickListener {

            if (holder.viewsFragmentHolder.socialText.text == "Google") {
                val intent = Intent(holder.viewsFragmentHolder.root.context, Options::class.java)
                holder.viewsFragmentHolder.root.context.startActivity(intent)
            } else if (holder.viewsFragmentHolder.socialText.text == "Facebook"){
                val intent = Intent(holder.viewsFragmentHolder.root.context, Options::class.java)
                holder.viewsFragmentHolder.root.context.startActivity(intent)
            }


        }*/
        holder.bind(list[position])

    }

    override fun getItemCount() = list.size


}

