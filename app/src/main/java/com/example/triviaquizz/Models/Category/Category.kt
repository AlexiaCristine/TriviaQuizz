package com.example.triviaquizz.Models.Category

class Category(
    var id : Long ,
    var name : String
){
    override fun equals(other : Any?) = other is Category && this.id == other.id
}