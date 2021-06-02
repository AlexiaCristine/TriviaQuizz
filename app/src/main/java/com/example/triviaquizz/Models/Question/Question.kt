package com.example.triviaquizz.Models.Question

import com.example.triviaquizz.Models.Category.Category

class Question (
    var question: String ,
    var category: Category ,
    var difficulty: String ,
    var answers: List<AnswerQuestion>
)