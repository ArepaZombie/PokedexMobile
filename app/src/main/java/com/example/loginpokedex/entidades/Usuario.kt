package com.example.loginpokedex.entidades

import android.app.admin.TargetUser
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Usuario")
data class Usuario(
  @PrimaryKey
  val id:Int,
  @ColumnInfo
  val user:String,
  @ColumnInfo
  val password:String
)
