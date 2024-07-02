package com.example.loginpokedex.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.api.api.PokeApi
import com.example.loginpokedex.R
import com.example.loginpokedex.databinding.ActivityDetallePokemonBinding
import com.example.loginpokedex.entidades.Pokemon
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetallePokemonActivity : AppCompatActivity() {
    lateinit var b:ActivityDetallePokemonBinding
    var nombre:String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        b = ActivityDetallePokemonBinding.inflate(layoutInflater)
        setContentView(b.root)
        //setContentView(R.layout.activity_detalle_pokemon)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val x = intent
        nombre = x.getStringExtra("nombre").toString().lowercase()
        buscarPokemon()
    }
    fun buscarPokemon(){
      var request = PokeApi.build().getPokemon(nombre)

      request.enqueue(object : Callback<Pokemon> {
        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
          var pokemon:Pokemon? = response.body()

          if (pokemon!=null){
            b.tvNombreD.setText(pokemon.name.uppercase())
            b.tvIdD.setText("#"+pokemon.id)
            b.tvHeightD.setText(pokemon.height.toString())
            b.tvWeightB.setText(pokemon.weight.toString())
            b.barHp.setProgress(pokemon.stats[0].base_stat)
            b.barAtk.setProgress(pokemon.stats[1].base_stat)
            b.barDef.setProgress(pokemon.stats[2].base_stat)
            b.barSpa.setProgress(pokemon.stats[3].base_stat)
            b.barSpd.setProgress(pokemon.stats[4].base_stat)
            b.barExp.setProgress(pokemon.stats[5].base_stat)
            Picasso.get().load(pokemon.sprites.front_default).into(b.fotoPokemon)

          }

        }

        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
          Toast.makeText(applicationContext, "Error: "+ t.message, Toast.LENGTH_SHORT).show()
        }
      })

    }
}
