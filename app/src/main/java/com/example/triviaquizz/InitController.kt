package com.example.triviaquizz

import android.app.Application
import android.content.Context
import com.example.triviaquizz.Models.Category.Category
import com.example.triviaquizz.Models.Game.Game
import com.example.triviaquizz.Models.Question.AnswerQuestion

class InitController: Application() {
    // é um public static
        companion object {
            var token = getAuthToken()
            var difficult = ""
            var category = Category(0,"")
            var game = Game("","", "",0)
            var endGame = false
            var question = ""
            var problem = false
            var answers = listOf<AnswerQuestion>()

            fun getAuthToken(): String {
                val token = MainApplication
                    .getContext()
                    ?.getSharedPreferences("login", Context.MODE_PRIVATE)
                    ?.getString("token", "")

                return token!!
            }
        }

}