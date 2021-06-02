package com.example.triviaquizz.NetworkServices

import com.example.triviaquizz.Models.Answer.AnswerResponde
import com.example.triviaquizz.Models.Question.QuestionResponse
import retrofit2.Call
import retrofit2.http.*

interface QuestionService {
    /**
     * @Headers("Content-Type:application/json; charset=UTF-8") -> Formata tudo para JSON
     * No contexto do código: Problem == Question
     * @Header("Authorization") -> Requisita o cabeçalho de autorização informando o token adquirido
     * no login
    */

    @POST("problems/answer")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun answer(
        @Query("answer") answer: Int,
        @Header("Authorization") authorization: String): Call<AnswerResponde>

    @GET("problems/next")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getNext(@Header("Authorization") authorization: String): Call<QuestionResponse>

    @GET("/problems/view")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun getActual(@Header("Authorization") authorization: String): Call<QuestionResponse>

}