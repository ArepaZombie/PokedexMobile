package com.example.loginpokedex.entidades

import java.sql.Types

data class Pokemon(
  var id:Int,
  var name: String,
  var height:Int,
  var weight:Int,
  var stats:List<Estadistica>,
  var sprites:Sprites,
  var types:List<TypesObject>

)
  data class TypesObject(
    var type: Type
  )
