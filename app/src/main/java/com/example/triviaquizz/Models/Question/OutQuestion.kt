package com.example.triviaquizz.Models.Question

import com.google.gson.annotations.SerializedName


class DataQuestion( // bloquinho da questão
    @SerializedName("problem")
    var question : Question
    )

class AnswerQuestion(  // bloquinho acessando descrição e ordem das respostas
    @SerializedName("description")
    var descrip: String,
    var order: Int
    )

class QuestionResponse(
    var status : String,
    var data : DataQuestion?
    )