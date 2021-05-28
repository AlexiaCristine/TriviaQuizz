package com.example.triviaquizz.DAO

import android.util.Log
import com.example.triviaquizz.Models.Category.ResponseCategory
import com.example.triviaquizz.NetworkServices.CategoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryDao {

    val catRetrofit =
        Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/")
            .addConverterFactory(
            GsonConverterFactory.create())
            .build()
    val catService = catRetrofit.create(CategoryService::class.java)

    fun getCategory(finished : (ResponseCategory) -> Unit) {
        catService.getCategory().enqueue(object : Callback<ResponseCategory> {
            override fun onResponse(call : Call<ResponseCategory>, response : Response<ResponseCategory>) {
                if (response.body() != null) {
                    val category = response.body()!!
                    finished(category)
                }
            }
            override fun onFailure(call : Call<ResponseCategory>, t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }
        })
    }
}