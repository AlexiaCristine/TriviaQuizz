package com.example.triviaquizz.Models.User

class inUser {
    data class User(
        var email: String,
        var name: String,
        var password: String,
        var token: String

    ) {
        var id: Long? = null
    }
}