package com.example.triviaquizz.Models.Game

import com.google.gson.annotations.SerializedName

class Game (
    @SerializedName("creation")
    var create: String,
    var status: String,
    @SerializedName("started_at")
    var startAt: String,
    var score: Int

    ) {


class GameData(
    var game: Game
)

class ResponseGame(
    var status: String,
    var data: GameData?
)}
