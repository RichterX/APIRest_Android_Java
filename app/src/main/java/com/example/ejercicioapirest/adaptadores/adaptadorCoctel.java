package com.example.ejercicioapirest.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ejercicioapirest.R;
import com.example.ejercicioapirest.modelos.Coctail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adaptadorCoctel extends BaseAdapter
{
    protected Context context;
    private int layout;
    private ArrayList<Coctail> cocteles;

    public adaptadorCoctel(Context context, int layout, ArrayList<Coctail> cocteles)
    {
        this.context = context;
        this.layout = layout;
        this.cocteles = cocteles;
    }

    @Override
    public int getCount()
    {
        return this.cocteles.size();
    }

    @Override
    public Object getItem(int i)
    {
        return this.cocteles.get(i);
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

        Coctail coctail = this.cocteles.get(i);

        //Obtengo los elementos de la vista
        TextView id = (TextView) v.findViewById(R.id.tv_id);
        String idCoctail = String.valueOf(coctail.getIdDrink());
        id.setText(idCoctail);

        TextView nombre = (TextView) v.findViewById(R.id.tv_nombre);
        nombre.setText(coctail.getstrDrink());

        ImageView imagen = (ImageView) v.findViewById(R.id.iv_coctail);
        Picasso.get().load(coctail.getstrDrinkThumb()+"/preview").into(imagen);

        return v;
    }
}
