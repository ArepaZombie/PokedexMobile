package com.example.loginpokedex.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpokedex.MainActivity
import com.example.loginpokedex.R
import com.example.loginpokedex.database.AppDB
import com.example.loginpokedex.databinding.ActivityIniciarSesionBinding
import com.example.loginpokedex.entidades.Usuario

class IniciarSesionActivity : AppCompatActivity() {

    lateinit var b: ActivityIniciarSesionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityIniciarSesionBinding.inflate(layoutInflater)
        setContentView(b.root)
        // setContentView(R.layout.activity_iniciar_sesion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        b.btnIniciarSesion.setOnClickListener {
          IniciarSesion()
        }

        b.btnRegistrar.setOnClickListener {
          var i = Intent(applicationContext,RegistrarUsuarioActivity::class.java)
          startActivity(i)
        }

    }
    fun IniciarSesion(){
        val user = b.edtUsuario.text.toString()
        val password = b.edtPassword.text.toString()

        val usuario:Usuario =
          AppDB.getDatabase(this).dao().IniciarSesion(user, password)

        if (usuario== null)
        {
          Toast.makeText(this, "Credenciales incorrectas.", Toast.LENGTH_SHORT).show()
        }
        else
        {
          val i = Intent(this,ListarPokemonesActivity::class.java)
          i.putExtra("n",0)
          startActivity(i)
        }

    }
}
