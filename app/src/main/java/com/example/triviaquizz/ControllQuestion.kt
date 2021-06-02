package com.example.triviaquizz

import com.example.triviaquizz.DAO.QuestionDao
import com.example.triviaquizz.Models.Question.QuestionResponse


class ControllQuestion {
    private val questiondao = QuestionDao()
    fun getNext(done : (QuestionResponse) -> Unit) {
        val token = InitController.token!!
        questiondao.getNext(token) { problemAPI ->
            done(problemAPI)
        }
    }

    fun getCurrent(done : (QuestionResponse) -> Unit) {
        val token = InitController.token!!
        questiondao.getCurrent(token) { problemAPI ->
            done(problemAPI)
        }
    }

    fun answer(answer : Int , done : (answer : Int) -> Unit) {
        val token = InitController.token!!
        questiondao.answer(token , answer) { answerAPI ->
            if(answerAPI.status == "error") {
                done(0)
            }
            else {
                done(answerAPI.data!!.answer.correctAnswer.order)
            }
        }
    }

}