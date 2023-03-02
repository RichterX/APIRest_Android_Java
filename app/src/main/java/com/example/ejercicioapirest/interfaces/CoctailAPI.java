package com.example.ejercicioapirest.interfaces;

import com.example.ejercicioapirest.modelos.Drinks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoctailAPI
{
    //Indicamos la URL base de la api a consumir (en este caso, la de bebidas)
    String BASE_URL = "https://www.thecocktaildb.com/";

    //Con esta query, buscamos y filtramos por letra, facilitándole la misma
    @GET ("api/json/v1/1/search.php?")
    public Call<Drinks> getDrinkByName(@Query("f") String letra);

    //En este caso, nos devuelve una bebida al azar
    @GET ("api/json/v1/1/random.php")
    public Call<Drinks> getRandomDrink();
}
