package com.example.triviaquizz.Models.User


data class OutUser(
    var status: String?,
    var dataUser: DataUser?, // OBJ
    var message: String?

    )

data class DataUser( // OBJ pego
    var user: UserRegister?
    )

data class UserRegister( //registrar
    val email: String?,
    val name: String?,
    val token: String?

    )




