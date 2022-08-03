package com.example.login_firebase

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login_firebase.databinding.FragmentListadoRedesCodeBinding
import com.example.login_firebase.dto.Code
import com.example.login_firebase.dto.WsClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class listadoRedesCode : Fragment() {

    private lateinit var views: FragmentListadoRedesCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        views = FragmentListadoRedesCodeBinding.inflate(inflater)
        cargarListado(inflater.context)
        return views.root

    }

    private fun cargarListado(context: Context) {
        WsClient.apiSocial()?.findcode()?.enqueue(object: Callback<List<Code>>{
            override fun onResponse(call: Call<List<Code>>, response: Response<List<Code>>) {
                if (response.isSuccessful){
                    response.body()?.let {
                        listaRedes ->
                        views.listaRedesCode.layoutManager = LinearLayoutManager(context)
                        views.listaRedesCode.adapter = AdapterCode(listaRedes)
                    }
                }

            }

            override fun onFailure(call: Call<List<Code>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}