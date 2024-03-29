package com.example.login_firebase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.FragmentListadoRedesMailBinding
import com.example.login_firebase.dto.Social
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listadoRedesMail : Fragment() {

    private lateinit var views: FragmentListadoRedesMailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = FragmentListadoRedesMailBinding.inflate(inflater)
        cargarListado(inflater.context)
        return views.root
    }

    private fun cargarListado(context: Context?) {
        views.listaRedesMail.layoutManager = LinearLayoutManager(context)
        views.listaRedesMail.adapter = Adapter(
            listOf(
                OpcionesSignIn("Google", "https://cdn-icons-png.flaticon.com/512/2702/2702602.png"),
                OpcionesSignIn("Apple", "https://cdn-icons-png.flaticon.com/512/270/270781.png"),
                OpcionesSignIn("Microsoft", "https://cdn-icons-png.flaticon.com/512/732/732221.png"),
                ), requireActivity()
        )

    }

}