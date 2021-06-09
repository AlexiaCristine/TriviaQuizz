package com.example.triviaquizz.Models.Category

import com.google.gson.annotations.SerializedName


class CategoryData(
    var categories : List<Category>
)

class ResponseCategory(
    var status: String,
    @SerializedName("data")
    var dataCat: CategoryData
)

