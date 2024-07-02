package com.example.loginpokedex.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.loginpokedex.entidades.Usuario

@Dao
interface UsuarioDAO {

  @Insert
  fun RegistrarUsuario(u:Usuario)

  @Query("select * from usuario where user=:user and password=:password")
  fun IniciarSesion(user:String, password:String):Usuario

  @Query("select * from usuario where id =:id")
  fun DetalleCliente(id:Int):Usuario

  @Query("select max(id) from usuario")
  fun UltimoId():Int

}
