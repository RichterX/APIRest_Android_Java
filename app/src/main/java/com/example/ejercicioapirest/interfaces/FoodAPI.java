package com.example.ejercicioapirest.interfaces;

import com.example.ejercicioapirest.modelos.Drinks;
import com.example.ejercicioapirest.modelos.Meals;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodAPI
{
    //Indicamos la URL base de la api a consumir (esta vez la de comidas)
    String BASE_URL = "https://www.themealdb.com/";

    //Con esta query, buscamos y filtramos por letra, facilitándole la misma
    @GET("api/json/v1/1/search.php?")
    public Call<Meals> getFoodByName(@Query("f") String letra);

    //En este caso, nos devuelve una comida al azar
    @GET ("api/json/v1/1/random.php")
    public Call<Meals> getRandomFood();
}
