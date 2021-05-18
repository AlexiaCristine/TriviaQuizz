package com.example.triviaquizz

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.triviaquizz.DAO.UserDao
import com.example.triviaquizz.Models.User.User
import kotlinx.android.synthetic.main.activity_register.*
abstract class RegisterActivity : AppCompatActivity() {

    private lateinit var dao: UserDao

    fun createDB() {
        dao = UserDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        createDB()
        btnRegister.setOnClickListener {

            val name = txName.text.trim().toString()
            val email = txEmail.text.trim().toString()
            val password = txPassword.text.trim().toString()
            val password2 = txPasswordConfirm.text.trim().toString()

            if (name.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && password2.isNotEmpty() && password == password2 ) {
                val user = User(name, email, password, "")
                add(user)
            } else {
                Toast.makeText(this, "Campos preenchidos de maneira invalida !", Toast.LENGTH_LONG).show()
            }
        }
        tvLogin.setOnClickListener {
            finish()
        }
    }

    fun add(user: User) {
        dao.insert(user){
            if(it.status == "success"){
                Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this, "Deu ruim!", Toast.LENGTH_LONG).show()

            }
        }

    }

}