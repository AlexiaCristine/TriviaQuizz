package com.example.triviaquizz.NetworkServices

import com.example.triviaquizz.Models.Category.ResponseCategory
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface CategoryService {

    @GET("categories")
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun getCategory(): Call<ResponseCategory>

}