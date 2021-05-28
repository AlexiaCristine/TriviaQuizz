package com.example.triviaquizz.Models.Answer

class AnswerData(
    var answer : Answer
)

class AnswerResponde(
    var status: String,
    var data: AnswerData?
)

class CorrectAnswer(
    var order: Int,
    var description: String
)
{

}