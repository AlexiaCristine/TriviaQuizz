package com.example.triviaquizz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaquizz.DAO.UserDao
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*


class LoginActivity : AppCompatActivity() {
    private lateinit var dao: UserDao

    fun createDB(){
        dao = UserDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isLoggedIn()){
            iniciarMain()
            return
        }
        setContentView(R.layout.activity_login)
        createDB()
        btnLogin.setOnClickListener{ // porque esse botão n ta indo ?

            val password = txPasswordm.text.trim().toString()
            val email = txEmailm.text.trim().toString()

            if (email != null && password != null) {
                val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    putLong("userId",  //inUser.User como eu faço pra isso deixar ue por o id aqui ?
                    )
                    commit()
                }
                val highScore = sharedPref.getInt("userId", 0)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "informação invalida", Toast.LENGTH_LONG).show();
            }
        }
        btnRegister.setOnClickListener{
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }


    private fun isLoggedIn() : Boolean {
        val id = this.getSharedPreferences("login", Context.MODE_PRIVATE).getLong("userId", 0)
        return !id.equals(0L)
    }

    private fun iniciarMain() {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }

}