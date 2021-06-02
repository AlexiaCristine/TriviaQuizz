package com.example.triviaquizz.DAO

import com.example.triviaquizz.Models.Answer.AnswerResponde
import com.example.triviaquizz.Models.Question.QuestionResponse
import com.example.triviaquizz.NetworkServices.QuestionService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class QuestionDao {
    val QRetrofit =
            Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/")
                .addConverterFactory(
                    GsonConverterFactory.create()
                ).build()
        val questService = QRetrofit.create(QuestionService::class.java)


        fun getNext(token:String, finished:(QuestionResponse)->Unit){
            questService.getNext(token).enqueue(object : Callback<QuestionResponse> {
                override fun onResponse(
                    call : Call<QuestionResponse>,
                    response : Response<QuestionResponse>
                ) {
                    if(response.isSuccessful) {
                        finished(response.body()!!)
                    } else {
                        finished(
                            QuestionResponse("error", null)
                        )
                    }
                }

            override fun onFailure(
                call : Call<QuestionResponse> , t : Throwable
            ){
                }
            })
        }

        fun getCurrent(token:String, finished:(QuestionResponse)->Unit){
            questService.getActual(token).enqueue(object : Callback<QuestionResponse> {
            override fun onResponse(
                call: Call<QuestionResponse>,
                response: Response<QuestionResponse>
                ) {
                     if(response.isSuccessful) {
                         finished(response.body()!!)
                     } else {
                         finished(QuestionResponse("error", null))
                     }
                }
                override fun onFailure(
                    call : Call<QuestionResponse> , t : Throwable
                ) {}
            })
        }

        fun answer(token:String, answer: Int,finished:(AnswerResponde)->Unit){
            questService.answer(answer,token)
                .enqueue(object : Callback<AnswerResponde> {
                    override fun onResponse(
                        call : Call<AnswerResponde> ,
                        response : Response<AnswerResponde>
                    )
                    {
                        if(response.isSuccessful) {
                            finished(response.body()!!)
                        } else {
                            finished(AnswerResponde("error", null))
                        }
                    }

                    override fun onFailure(call : Call<AnswerResponde> , t : Throwable) {}
            })
        }

}