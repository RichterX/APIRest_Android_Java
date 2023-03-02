package com.example.ejercicioapirest.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Meals
{
    ////////  ATRIBUTOS  ///////////
    //Esta clase recogerá el resultado del JSON y lo almacenará en una listview para consultarla luego
    @SerializedName("meals")
    @Expose
    private List<Food> meals = new ArrayList<Food>();

    private ArrayList<Meals> results;

    ////////  MÉTODOS  ////////
    public List<Food> getMeals()
    {
        return meals;
    }

    public ArrayList<Meals> getResults()
    {
        return results;
    }

    public void setResults(ArrayList<Meals> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "Meals{" +
                "Food=" + meals +
                '}';
    }
}
