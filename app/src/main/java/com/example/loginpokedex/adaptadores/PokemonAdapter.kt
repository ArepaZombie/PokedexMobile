package com.example.pokedexcibertec.UI

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.api.api.PokeApi
import com.example.loginpokedex.R
import com.example.loginpokedex.entidades.Pokemon
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokemonAdapter(
    context: Context,
    private val pokemonList: List<Pokemon>
) : ArrayAdapter<Pokemon>(context, R.layout.activity_fila_pokemon_cardview, pokemonList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.activity_fila_pokemon_cardview, parent, false)
            holder = ViewHolder(
                view.findViewById(R.id.imgPoke),
                view.findViewById(R.id.tvNombre),
                view.findViewById(R.id.tvTipo),
                view.findViewById(R.id.tvId)
            )
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }

        val nombrepokemon = pokemonList[position].name
        val request = PokeApi.build().getPokemon(nombrepokemon)
      request.enqueue(object : Callback<Pokemon> {
        override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {

          val pokemon:Pokemon? = response.body()

          // Seteando el nombre del Pokémon
          if (pokemon != null) {
            holder.nombreTextView.text = pokemon.name.uppercase() ?: ""

            // Seteando los tipos del Pokémon
            val tipos = pokemon.types?.joinToString("\n ") { it.type.name ?: "" } ?: ""
            holder.tipoTextView.text = "Tipos:\n $tipos"

            // Seteando el número de identificación del Pokémon
            holder.idTextView.text = "#${pokemon.id}"

            Picasso.get().load(pokemon.sprites.front_default).into(holder.imagenImageView)
          }
        }

        override fun onFailure(call: Call<Pokemon>, t: Throwable) {
          Toast.makeText(context.applicationContext, "Error: "+ t.message, Toast.LENGTH_SHORT).show()
        }
      })

        return view!!
    }

    private data class ViewHolder(
      val imagenImageView: ImageView,
      val nombreTextView: TextView,
      val tipoTextView: TextView,
      val idTextView: TextView
    )
}
