package com.example.triviaquizz.Models.User

data class UserError (
    var status: String,
    var data: DataError
)

data class DataError (
    var errors: Errors
)

data class Errors(
    var name: List<String>?,
    var email: List<String>?,
    var password: List<String>?
)
