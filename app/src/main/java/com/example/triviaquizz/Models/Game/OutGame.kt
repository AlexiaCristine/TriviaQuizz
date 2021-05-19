package com.example.triviaquizz.Models.Game

import com.google.gson.annotations.SerializedName

class OutGame (
    var status : String,
    @SerializedName("started_at")
    var startedAt : String,
    @SerializedName("finished_at")
    var finishedAt : String,
    var score : Int

)

class OutGameDatabase(
    var game : OutGame
)

class OutGameResponse(
    var status: String,
    var data: OutGameDatabase?
){
}