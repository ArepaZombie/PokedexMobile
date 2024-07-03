package com.example.api.api


import com.example.loginpokedex.entidades.Pokemon
import com.example.loginpokedex.entidades.ResponsePokemon
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object PokeApi {

    private val builder = Retrofit.Builder()
        .baseUrl(Constante.ruta_base)
        .addConverterFactory(GsonConverterFactory.create())

    interface  ServicioWeb{
        @GET("pokemon")
        fun getPokemones(@Query("offset") n:Int, @Query("limit") limit:Int=5): Call<ResponsePokemon>

        @GET("pokemon/{nombre}")
        fun getPokemon(@Path("nombre") nombre:String): Call<Pokemon>
    }

    // devolver la instancia del ServicioWeb
    fun build(): ServicioWeb {
        return builder.build().create(ServicioWeb::class.java)
    }

}
