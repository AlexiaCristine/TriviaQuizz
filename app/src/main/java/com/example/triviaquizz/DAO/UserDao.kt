package com.example.triviaquizz.DAO

import com.example.triviaquizz.Models.User.*
import com.example.triviaquizz.NetworkServices.UserService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserDao {

    val url = "https://super-trivia-server.herokuapp.com/"

    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/")
        .addConverterFactory(GsonConverterFactory.create()).build()

    val userservice = retrofit.create(UserService::class.java)

    fun getAll(){
        userservice.getAll().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val user =  response.body()!!
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
            }

        })
    }

    fun insert(user : User, finished : (user : OutUser) -> Unit) {
        userservice.insert(user).enqueue(object : Callback<OutUser> {
            override fun onResponse( call : Call<OutUser>, response : Response<OutUser>){
                if (response.body() != null) {
                    val userAPI = response.body()!!
                    finished(userAPI)
                } else {
                    val response = OutUser("error" , null, "ErrorMenssager")
                    finished(response)
                }
                finished(response.body()!!)
            }
            override fun onFailure(call : Call<OutUser> , t : Throwable) {
            }

        })

    }
    fun login(login : Login, finished : (login : ResponseLogin) -> Unit) {
        userservice.login(login).enqueue(object : Callback<ResponseLogin> {
            override fun onResponse(
                    call : Call<ResponseLogin> ,
                    response : Response<ResponseLogin>
            ) {
                if (response.body() != null) {
                    val loginAPI = response.body()!!
                    finished(loginAPI)
                } else {
                    val response = ResponseLogin("error" , null)
                    finished(response)
                }
            }

            override fun onFailure(call : Call<ResponseLogin> , t : Throwable) {
            }

        })
    }
}