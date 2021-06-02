package com.example.triviaquizz.NetworkServices

import com.example.triviaquizz.Models.Game.Game
import com.example.triviaquizz.Models.Game.OutGameResponse
import retrofit2.Call
import retrofit2.http.*

interface GameService {

    @GET("games")
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON

    fun startG(
        @Query("difficulty") difficulty: String,
        @Query("category_id") category_id: Long,
        @Header("Authorization") authorization: String,
    ) : Call<Game.ResponseGame>

    @DELETE("games")
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun endGame(@Header("Authorization") authorization: String): Call<OutGameResponse>
}