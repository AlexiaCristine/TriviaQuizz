package com.example.triviaquizz.Models.Answer

class AnswerData(
    var answer: Answer
)

class AnswerResponse(
    var status: String,
    var data: AnswerData?
)

class CorrectAnswer(
    var order: Int,
)
