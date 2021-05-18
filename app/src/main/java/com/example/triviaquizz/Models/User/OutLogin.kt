package com.example.triviaquizz.Models.User

data class Login( //Logar
        var email: String,
        var password: String

    )
data class DataLogin( //obj
        var user : User
    )

data class ResponseLogin( //resposta do login
        var status: String,
        var data : DataLogin?
    )