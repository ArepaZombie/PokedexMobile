package com.example.loginpokedex

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpokedex.ui.IniciarSesionActivity

class MainActivity : AppCompatActivity() {
    lateinit var btn:ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
      btn = findViewById(R.id.btnIniciar)
      btn.setOnClickListener { IniciarAplicacion() }
    }
  fun IniciarAplicacion(){
    val i = Intent(this,IniciarSesionActivity::class.java)
    startActivity(i)
  }
}
