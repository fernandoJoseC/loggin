package com.example.login_firebase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.FragmentListadoRedesSocialBinding
import com.example.login_firebase.dto.Social
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listadoRedesSocial : Fragment() {

    private lateinit var views: FragmentListadoRedesSocialBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = FragmentListadoRedesSocialBinding.inflate(inflater)
        cargarListado(inflater.context)
        return views.root
    }

    private fun cargarListado(context: Context?) {
        views.listaRedesSocial.layoutManager = LinearLayoutManager(context)
        views.listaRedesSocial.adapter = Adapter(
            listOf(
                OpcionesSignIn("Facebook", "https://cdn-icons-png.flaticon.com/512/733/733547.png"),
                OpcionesSignIn("Twitter", "https://cdn-icons-png.flaticon.com/512/179/179342.png")
            ), requireActivity()
        )

    }

}