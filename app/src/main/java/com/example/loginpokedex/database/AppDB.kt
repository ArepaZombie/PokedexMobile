package com.example.loginpokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.loginpokedex.dao.UsuarioDAO
import com.example.loginpokedex.entidades.Usuario

@Database(entities = [Usuario::class], version = 1)
abstract class AppDB:RoomDatabase() {

  abstract fun dao():UsuarioDAO

  companion object{
    private var instanciadb: AppDB? = null

    fun getDatabase(contexto:Context):AppDB
    {
      if (instanciadb == null)
      {
        instanciadb = Room.databaseBuilder(
          contexto.applicationContext,
          AppDB::class.java, "Usuarios.db"
        ).allowMainThreadQueries().build()
      }
      //
      return instanciadb!!;
    }
  }

}
