package com.example.triviaquizz.Fragments

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.triviaquizz.Adapters.AdapterQuestion
import com.example.triviaquizz.Controllers.ControllQuestion
import com.example.triviaquizz.Controllers.InitController
import com.example.triviaquizz.R
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_game.view.*


class Game : Fragment() {
        private lateinit var problemAdapter : AdapterQuestion
        val problemController = ControllQuestion()

        override fun onCreateView(
            inflater : LayoutInflater , container : ViewGroup? ,
            savedInstanceState : Bundle?
        ) : View? {
            val view = inflater.inflate(R.layout.fragment_game , container , false)
            setView(view)
            return view
        }

        fun setView(view : View) {
            Log.d("appState", InitController.Companion.toString())
            if (InitController.problem) {
                getCurrent(view)
            } else {
                getNext(view)
            }
            view.listAnswers.layoutManager = LinearLayoutManager(
                context ,
                LinearLayoutManager.VERTICAL ,
                false
            )

        }

        private fun getCurrent(view : View) {
            val build: AlertDialog.Builder = AlertDialog.Builder(activity)
            build.setView(R.layout.activity_loading)
            build.setCancelable(false)
            val load: AlertDialog = build.create()
            problemController.getCurrent { problemAPI ->
                openQuestion(view)
                InitController.difficult = problemAPI.data!!.question.difficulty
                InitController.category = problemAPI.data!!.question.category
                InitController.question = problemAPI.data!!.question.question
                InitController.answers = problemAPI.data!!.question.answers
                questionGame.text = problemAPI.data!!.question.question
                difficultGame.text = problemAPI.data!!.question.difficulty
                categoryGame.text = problemAPI.data!!.question.category.name
                if (InitController.game.score > 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#008000"))
                } else if (InitController.game.score < 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#ff0000"))
                }
                valueScoreGame.text = InitController.game.score.toString()
                view.listAnswers.adapter = AdapterQuestion(problemAPI.data!!.question.answers , view)
                load.dismiss()
            }

        }

        private fun getNext(view : View) {
            val build: AlertDialog.Builder = AlertDialog.Builder(activity)
            build.setView(R.layout.activity_loading)
            build.setCancelable(false)
            val load: AlertDialog = build.create()
            problemAdapter = AdapterQuestion(listOf(),view)
            load.show()
            problemController.getNext { problemAPI ->
                InitController.difficult = problemAPI.data!!.question.difficulty
                InitController.category = problemAPI.data!!.question.category
                InitController.question = problemAPI.data!!.question.question
                InitController.answers = problemAPI.data!!.question.answers
                questionGame.text = problemAPI.data!!.question.question
                difficultGame.text = problemAPI.data!!.question.difficulty
                categoryGame.text = problemAPI.data!!.question.category.name
                if (InitController.game.score > 0) {
                    valueScoreGame.setTextColor(Color.parseColor("#008000"))
                } else if (InitController.game.score <0) {
                    valueScoreGame.setTextColor(Color.parseColor("#ff0000"))
                }
                valueScoreGame.text = InitController.game.score.toString()
                view.listAnswers.adapter = AdapterQuestion(problemAPI.data!!.question.answers , view)
                InitController.problem = true
                load.dismiss()
            }
        }

        private fun openQuestion(view : View) {
            val mAlertDialog = androidx.appcompat.app.AlertDialog.Builder(view.context)
            mAlertDialog.setTitle(R.string.app_name)
            mAlertDialog.setMessage(resources.getString(R.string.open_question))
            mAlertDialog.setPositiveButton("Yes") { dialog , id ->
                val navController = Navigation.findNavController(view)
                navController.navigate(R.id.action_game_to_resume)
            }
            mAlertDialog.setNegativeButton("No") { dialog , id ->
            }
            mAlertDialog.show()
        }

    }
