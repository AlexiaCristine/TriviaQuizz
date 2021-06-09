package com.example.triviaquizz.Fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.triviaquizz.ControllGame
import com.example.triviaquizz.InitController
import com.example.triviaquizz.R
import kotlinx.android.synthetic.main.fragment_resume.*

import com.example.triviaquizz.Models.Category.Category as CategoryModel
import com.example.triviaquizz.Models.Game.Game as GameModel


class Resume : Fragment() {
    private lateinit var gameController : ControllGame
    var navController : NavController? = null

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_resume , container , false)
        val build: AlertDialog.Builder = AlertDialog.Builder(view.context)
            .setView(R.layout.activity_loading)
            .setCancelable(false)
        val load = build.create()
        setView(view,load)

        return view
    }

    private fun setView(view : View , load : AlertDialog) {

        gameController = ControllGame()
        load.show()
        gameController.endGame { endGame ->
            load.dismiss()
            InitController.endGame = true
            val game = endGame
            if (game.data!!.game.score> 0) {
                valueScoreResume.setTextColor(Color.parseColor("#008000"))
            } else {
                valueScoreResume.setTextColor(Color.parseColor("#ff0000"))
            }
            valueScoreResume.text = game.data!!.game.score.toString()
            valueDifficultResume.text = InitController.difficult
            valueCategoryResume.text = InitController.category.name

        }
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        btnResumeBack.setOnClickListener {
            InitController.difficult = ""
            InitController.category = CategoryModel(0 , "")
            InitController.game = GameModel("" , "" , "" , 0)
            InitController.endGame = true
            InitController.question = ""
            InitController.problem = false

            navController!!.navigate(R.id.action_resumeFragment_to_gameFragment)

        }
    }

}