package com.example.triviaquizz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.triviaquizz.DAO.UserDao
import com.example.triviaquizz.Models.User.inUser
import kotlinx.android.synthetic.main.activity_register.*
class RegisterActivity {

    private lateinit var dao: UserDao

    fun createDB() {
        dao = UserDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        createDB()
        btnRegister.setOnClickListener {

            val Name = txName.text.trim().toString()
            val Email = txEmail.text.trim().toString()
            val password = txPassword.text.trim().toString()
            val password2 = txPasswordConfirm.text.trim().toString()

            if (username.isNotEmpty() || password.isNotEmpty()) {
                val user = inUser.User(username, password)
                add(user)
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "registro invalida", Toast.LENGTH_LONG).show()
            }
        }
        tvLogin.setOnClickListener {
            finish()
        }
    }

    fun add(user: User) {
        dao.insert(user){


        }
    }


}