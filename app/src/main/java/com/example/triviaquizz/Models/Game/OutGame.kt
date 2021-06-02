package com.example.triviaquizz.Models.Game

import com.google.gson.annotations.SerializedName

class OutGame ( // aqui é pra acabar mesmo
    var status : String,
    @SerializedName("started_at")
    var startedAt : String,
    @SerializedName("finished_at")
    var finishedAt : String,
    var score : Int

)

class OutGameData( // aqui é pra pegar a database do jogo terminado
    var game : OutGame
)

class OutGameResponse( // aqui é o response pra terminar o jogo
    var status: String,
    var data: OutGameData?
){
}