package com.example.triviaquizz.NetworkServices

import com.example.triviaquizz.Models.User.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @POST("users")
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun insert(@Body user: User): Call<OutUser>

    @POST("auth")
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun login(@Body login: Login): Call<ResponseLogin> // resposta da api pro login

    //register
}