package com.example.ejercicioapirest.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejercicioapirest.R;
import com.example.ejercicioapirest.modelos.Food;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adaptadorPlato extends BaseAdapter
{
    protected Context context;
    private int layout;
    private ArrayList<Food> platos;

    public adaptadorPlato(Context context, int layout, ArrayList<Food> platos)
    {
        this.context = context;
        this.layout = layout;
        this.platos = platos;
    }

    @Override
    public int getCount()
    {
        return this.platos.size();
    }

    @Override
    public Object getItem(int i)
    {
        return this.platos.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        //Copio la vista
        View v = view;

        //Inflamos la vista con nuestro propio layout
        LayoutInflater inf = (LayoutInflater) LayoutInflater.from(this.context);

        //valor actual según la posición
        v = inf.inflate(R.layout.list_item, null);

        Food food = this.platos.get(i);

        //Obtengo los elementos de la vista
        TextView id = (TextView) v.findViewById(R.id.tv_id);
        String idPlato = String.valueOf(food.getIdMeal());
        id.setText(idPlato);

        TextView nombre = (TextView) v.findViewById(R.id.tv_nombre);
        nombre.setText(food.getStrMeal());

        ImageView imagen = (ImageView) v.findViewById(R.id.iv_coctail);
        Picasso.get().load(food.getStrMealThumb()+"/preview").into(imagen);

        return v;
    }
}
