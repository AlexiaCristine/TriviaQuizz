package com.example.triviaquizz.DAO

import com.example.triviaquizz.Models.User.OutUser
import com.example.triviaquizz.Models.User.UserError
import com.example.triviaquizz.Models.User.inUser
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

    // Tá, o OutUser já tem o Data como um atributo desse tipo, ele não é uma subclasse do OutUser
    // por isso não é possível usar: OutUser.Data

    // Imagina que na Activity (ou fragment, sei la) tu chama a funcao:
    /**
     * new UserDao().insert(passa o usuario aqui,
     * Primeiro callback (de sucesso)
     * {
     *  user: OutUser ->
     *      Acessa user aqui e faz o que tem que fazer, mostra sucesso e etc
     *  },
     *  Segundo callback (de falha)
     *  {
     *  error: UserError ->
     *      Cuida das excessões, exibe mensagem de erro e etc
     * })
     * acho que eu entendi
     * Única coisa que não sei se ele faz automatico é a conversão de Json pra essa class UserError
     * Tem que ver na docs do RetroFit como funciona ou ver se Stiehl menciona nos videos
     * okok :) eu tenho que ir dormir agr, mas eu acho que entendi
     * Beleza, por enquanto faz só como se não existisse falha, só dei a ideia aqui pra tu já ficar ciente desse caso oko
     * valeu de vdd :)
     * De boa, boa noite. Vou mimir também. Flws ;)
     */

    fun insert(user : inUser.User, finished : (user : OutUser) -> Unit,  fail: (response: UserError?) -> Unit) {
        userservice.insert(user).enqueue(object : Callback<OutUser> {
            override fun onResponse( call : Call<OutUser>, response : Response<OutUser>){
                if (response.body() != null) {
                    val userAPI = response.body()!!
                    finished(userAPI)
                } else {
                    val response = OutUser("error" , null, "Mensagem de erro aqui")
                    finished(response)}
                }

            /**
             * Aviso:
             * Me enganei, tu não passa no onFailure
             * Nesse caso, tu cria mais um callback la em cima pra caso de erro
             * Ai tu passa duas funções uma que é o finished (que é pra sucesso) e o fail que é pra falha
             * onde o callback ?
             */
            override fun onFailure(call : Call<OutUser> , t : Throwable) {
            }

        })
    }




}