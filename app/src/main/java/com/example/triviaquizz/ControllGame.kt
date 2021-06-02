package com.example.triviaquizz

import com.example.triviaquizz.DAO.GameDao
import com.example.triviaquizz.Models.Category.Category
import com.example.triviaquizz.Models.Game.Game
import com.example.triviaquizz.Models.Game.OutGameResponse

class ControllGame{
    val gamedao = GameDao()

    fun start(done : (game : Game.ResponseGame) -> Unit) {
        if (InitController.endGame)
        {
            InitController.difficult = ""
            InitController.category = Category(0 , "")
            InitController.game = Game("" , "" , "" , 0)
            InitController.endGame = false
            InitController.question = ""
            InitController.problem = false

        }
        val token = InitController.token!!
        val difficulty = InitController.difficult
        val category = InitController.category.id
        gamedao.startG(difficulty , category , token) { gameAPI ->
            done(gameAPI)
        }
    }

    fun endGame(done : (game : OutGameResponse) -> Unit) {
        val token = InitController.token!!.toString()
        gamedao.endGame(token) { gameAPI ->
            done(gameAPI)
        }
    }

}