package com.example.login_firebase.dto

class Send private constructor(
    val nombre: String? = null
){
    data class Builder(
        var nombre: String? = null
    ) {
        fun name(nombre: String?) = apply { this.nombre = nombre }
        fun build() = Send(nombre)
    }
}