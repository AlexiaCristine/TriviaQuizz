package com.example.triviaquizz.NetworkServices

import com.example.triviaquizz.Models.Answer.AnswerResponde
import com.example.triviaquizz.Models.Question.QuestionResponse
import retrofit2.Call
import retrofit2.http.*

interface QuestionService {

    @POST("problems/answer")
    // aqui eu deixei problems mesmo pq é esse o caminho da API, mas problems == Question
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun answer(
        @Query("answer") answer : Int,
        @Header("Authorization") authorization : String
    ) : Call<AnswerResponde>

    @GET("problems/next")
    // aqui eu deixei problems mesmo pq é esse o caminho da API, mas problems == Question
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun getNext(@Header("Authorization")
                authorization :
                String) :
            Call<QuestionResponse>

    @GET("/problems/view")
    // aqui eu deixei problems mesmo pq é esse o caminho da API, mas problems == Question
    @Headers("Content-Type:application/json; charset=UTF-8")
    // esse aqui serve pra trazer tudo no formato JSON
    fun getActual(@Header("Authorization")
                   authorization :
                   String) :
            Call<QuestionResponse>

}