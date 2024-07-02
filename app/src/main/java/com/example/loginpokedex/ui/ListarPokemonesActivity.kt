package com.example.loginpokedex.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.api.api.PokeApi
import com.example.loginpokedex.R
import com.example.loginpokedex.entidades.Pokemon
import com.example.loginpokedex.entidades.ResponsePokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListarPokemonesActivity : AppCompatActivity() {
    lateinit var lv: ListView
    lateinit var btnsiguiente:Button
    lateinit var btnanterior:Button
    var pagina = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_listar_pokemones)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
      val x = intent
      pagina = x.getIntExtra("n",0)

      lv = findViewById(R.id.lvPokedex)
      btnsiguiente = findViewById(R.id.btnAdelante)
      btnanterior = findViewById(R.id.btnAtras)


      if (pagina==0) btnanterior.isEnabled = false
      else{
        btnanterior.setOnClickListener {
          val i = Intent(this,ListarPokemonesActivity::class.java)
          i.putExtra("n",pagina-10)
          startActivity(i)
        }
      }

      btnsiguiente.setOnClickListener {
        val i = Intent(this,ListarPokemonesActivity::class.java)
        i.putExtra("n",pagina+10)
        startActivity(i)
      }

      //Toast.makeText(applicationContext, "${pagina}", Toast.LENGTH_SHORT).show()
      traerPokemones()

      lv.setOnItemClickListener { parent, view, position, id ->
        val nombre = parent.getItemAtPosition(position).toString()
        val i = Intent(applicationContext, DetallePokemonActivity::class.java)
        i.putExtra("nombre",nombre)
        startActivity(i)
      }





    }

  fun traerPokemones(){
    var request = PokeApi.build().getPokemones(pagina)

    request.enqueue(object : Callback<ResponsePokemon> {
      override fun onResponse(call: Call<ResponsePokemon>, response: Response<ResponsePokemon>) {
        var pokemones = ArrayList<String>()

        response.body()!!.results.forEach {
          pokemones.add("${it.name.capitalize()}")
        }

        var adp = ArrayAdapter<String>(
          applicationContext,
          android.R.layout.simple_list_item_1,
          pokemones
        )
        lv.adapter = adp
      }

      override fun onFailure(call: Call<ResponsePokemon>, t: Throwable) {
        Toast.makeText(applicationContext, "Error: "+ t.message, Toast.LENGTH_SHORT).show()
      }
    })


  }

}
