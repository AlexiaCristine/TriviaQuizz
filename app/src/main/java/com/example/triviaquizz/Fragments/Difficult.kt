package com.example.triviaquizz.Fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.triviaquizz.Controllers.ControllGame
import com.example.triviaquizz.Controllers.InitController
import com.example.triviaquizz.Activitys.LoginActivity
import com.example.triviaquizz.R
import kotlinx.android.synthetic.main.fragment_difficult.view.*

class Difficult : Fragment() {
    lateinit var gameController : ControllGame
    var navController : NavController? = null
    lateinit var build : AlertDialog.Builder

    override fun onCreateView(
        inflater : LayoutInflater , container : ViewGroup? ,
        savedInstanceState : Bundle?
    ) : View? {
        val view = inflater.inflate(R.layout.fragment_difficult , container , false)
        build = AlertDialog.Builder(view.context)
            .setView(R.layout.activity_loading)
            .setCancelable(false)

        gameController = ControllGame()
        return view
    }

    override fun onViewCreated(view : View , savedInstanceState : Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        navController = Navigation.findNavController(view)
        view.btnEasy.setOnClickListener { setDifficulty("easy") }
        view.btnMedium.setOnClickListener { setDifficulty("medium") }
        view.btnHard.setOnClickListener { setDifficulty("hard") }
        view.btnLogout.setOnClickListener { logout() }
    }

    private fun logout() {
        val pref = activity?.getSharedPreferences("login", Context.MODE_PRIVATE)
        pref?.edit()?.apply{
            putString("login", "false")
            putString("token", "")
            commit()
        }

        InitController.token = ""

        val intent = Intent(this.context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


    private fun setDifficulty(difficulty: String) {
        InitController.difficult = difficulty
        navController!!.navigate(R.id.action_difficult_to_category)
    }
}