package com.example.triviaquizz.NetworkServices
import com.example.triviaquizz.Models.User.OutUser
import com.example.triviaquizz.Models.User.inUser
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {

    @POST("users")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun insert(@Body user: inUser.User): Call<OutUser>
    /**
     * Aqui tu tinha que definir o tipo de retorno da função
     * Como tu não tinha definido, provavelmente ele retornava um void
     * void é basicamente um "null", ou seja não tem como tu chamar metodos ou acessar atributos
     * porque void em si é "vazio"
     * Quando tu define do tipo Call, voce basicamente avisa que o retorno é possível de ser acessado e manipulado pelo
     * retrofit
     * Capitche?
     * Capitche :)
     */
}