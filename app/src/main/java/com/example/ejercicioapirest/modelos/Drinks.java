package com.example.ejercicioapirest.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Drinks
{
    ////////  ATRIBUTOS  ///////////
    //Esta clase recogerá el resultado del JSON y lo almacenará en una listview para consultarla luego
    @SerializedName("drinks")
    @Expose
    private List<Coctail> drinks = new ArrayList<Coctail>();

    private ArrayList<Drinks> results;

    ////////  MÉTODOS  ////////
    public List<Coctail> getDrinks()
    {
        return drinks;
    }

    public ArrayList<Drinks> getResults()
    {
        return results;
    }

    public void setResults(ArrayList<Drinks> results)
    {
        this.results = results;
    }

    @Override
    public String toString()
    {
        return "Drinks{" +
                "drinks=" + drinks +
                '}';
    }
}
