package com.example.triviaquizz.Models.Category


class CategoryData(
    var categories : List<Category>
)

class ResponseCategory(
    var status: String,
    var dataCat: CategoryData
)
{

}

