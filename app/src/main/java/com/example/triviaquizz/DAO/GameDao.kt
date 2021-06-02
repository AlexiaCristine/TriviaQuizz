package com.example.triviaquizz.DAO

import android.util.Log
import com.example.triviaquizz.Models.Game.Game
import com.example.triviaquizz.Models.Game.OutGameResponse
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
        authorization : String ,
        finished : (Game.ResponseGame) -> Unit
    ) {

        gameService.startG(difficulty, category_id, authorization).enqueue(object :
            Callback<Game.ResponseGame> {
                override fun onResponse(
                    call: Call<Game.ResponseGame>,
                    response: Response<Game.ResponseGame>
                ) {
                    if(response.isSuccessful) { // sucesso se o status HTTP retornar entre 200 e 300
                        finished(response.body()!!)
                    }
                }

                override fun onFailure(call : Call<Game.ResponseGame>, t : Throwable) {
                    Log.i("Failure" ,
                        t.message.toString())
                }

            })
    }
    fun endGame(authorization : String , finished : (OutGameResponse) -> Unit) {
        gameService.endGame(authorization).enqueue(object : Callback<OutGameResponse> {
            override fun onResponse(
                call : Call<OutGameResponse> ,
                response : Response<OutGameResponse>
            ) {

                if(response.isSuccessful) {
                    finished(response.body()!!)
                }
            }

            override fun onFailure(call : Call<OutGameResponse> , t : Throwable) {
                Log.i("Failure" , t.message.toString())
            }

        })
    }
}