package com.example.triviaquizz.DAO

import android.util.Log
import com.example.triviaquizz.Models.Game.Game
import com.example.triviaquizz.NetworkServices.GameService
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GameDao {
    val gson = GsonBuilder().create()

    val retrofitG : Retrofit = Retrofit.Builder().
        baseUrl("https://super-trivia-server.herokuapp.com/").
        addConverterFactory(GsonConverterFactory.create(gson)).build()
    val gameService = retrofitG.create(GameService::class.java)

    fun startG(
        difficulty : String ,
        category_id : Long ,
        finished : (Game.ResponseGame) -> Unit
    ) {

        gameService.startG(difficulty, category_id).enqueue(object : Callback<Game.ResponseGame> {
                override fun onResponse(
                    call: Call<Game.ResponseGame>,
                    response: Response<Game.ResponseGame>
                ) {
                    Log.i("Failure" , response.body()!!.toString())
                    if (response.body() != null) {
                        if (response.body()!!.status == "success") {
                            finished(response.body()!!)}

                    }
                }

                override fun onFailure(call : Call<Game.ResponseGame>, t : Throwable) {
                    Log.i("Failure" , t.message.toString())
                }

            })
    }
}