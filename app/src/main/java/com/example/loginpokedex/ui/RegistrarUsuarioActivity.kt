package com.example.loginpokedex.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpokedex.MainActivity
import com.example.loginpokedex.R
import com.example.loginpokedex.database.AppDB
import com.example.loginpokedex.databinding.ActivityRegistrarUsuarioBinding
import com.example.loginpokedex.entidades.Usuario

class RegistrarUsuarioActivity : AppCompatActivity() {

  lateinit var b: ActivityRegistrarUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(b.root)
        //setContentView(R.layout.activity_registrar_usuario)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

      b.btnRegistrarUsuario.setOnClickListener { RegistrarUsuario() }

    }
    fun RegistrarUsuario(){
      val user = b.edtUsuarioRegistro.text.toString()
      val password = b.edtPasswordRegistro.text.toString()
      var id = AppDB.getDatabase(this).dao().UltimoId()

      if (id==null) id=1;
      else id++;

      val usuario = Usuario(id,user,password)

      AppDB.getDatabase(this).dao().RegistrarUsuario(usuario)

      Toast.makeText(this, "Usuario registrado!", Toast.LENGTH_SHORT).show()

      val i = Intent(this, IniciarSesionActivity::class.java)
      startActivity(i)


    }
}
