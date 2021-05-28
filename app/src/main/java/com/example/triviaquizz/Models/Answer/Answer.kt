package com.example.triviaquizz.Models.Answer

import com.google.gson.annotations.SerializedName

class Answer (
    var status: String,
    @SerializedName("correct_answer")
    var correctAnswer : CorrectAnswer,
    var score: Int
)
{
}