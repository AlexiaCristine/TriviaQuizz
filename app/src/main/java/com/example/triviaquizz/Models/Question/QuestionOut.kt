package com.example.triviaquizz.Models.Question


class DataQuestion( // bloquinho da questão
    var question : Question
    )

class AnswerQuestion(  // bloquinho acessando descrição e ordem das respostas
    var descrip: String,
    var order: Int
    )

class QuestionResponse(
    var status : String ,
    var data : DataQuestion?
    )