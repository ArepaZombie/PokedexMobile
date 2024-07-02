package com.example.loginpokedex.entidades

data class Pokemon(
  var id:Int,
  var name: String,
  var height:Int,
  var weight:Int,
  var stats:List<Estadistica>,
  var sprites:Sprites
)
