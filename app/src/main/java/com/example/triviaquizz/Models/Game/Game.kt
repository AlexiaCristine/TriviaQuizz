package com.example.triviaquizz.Models.Game

import com.google.gson.annotations.SerializedName

class Game (
    var create : String,
    var status : String,
    var score : Int,
    @SerializedName("start_at")
    var startAt : String

    )
class GameData(
    var game : Game
)

class ResponseGame(
    var status: String,
    var data: GameData?
)
{
}