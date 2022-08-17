package com.example.login_firebase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.FragmentBusquedaBinding
import com.example.login_firebase.dto.Social

class busqueda(val lista: List<OpcionesSignIn>) : Fragment() {

    private lateinit var views: FragmentBusquedaBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = FragmentBusquedaBinding.inflate(inflater)
        cargarListado(inflater.context)
        return views.root
    }

    private fun cargarListado(context: Context) {
        views.listaBusqueda.layoutManager = LinearLayoutManager(context)
        views.listaBusqueda.adapter = Adapter(lista, requireActivity())

    }
}