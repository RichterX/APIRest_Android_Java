package com.example.ejercicioapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetalleCoctail extends AppCompatActivity
{
    TextView nombreCoctail, idCoctail, descripcionCoctail;
    TextView ingre1, ingre2, ingre3, ingre4, ingre5, ingre6, ingre7, ingre8, ingre9, ingre10, ingre11, ingre12, ingre13, ingre14, ingre15;
    ImageView imagenCoctail;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_coctail);

        //Asiganmos los elementos de la vista
        nombreCoctail = (TextView) findViewById(R.id.tv_nombre);
        idCoctail = (TextView) findViewById(R.id.tv_idCoctail);
        descripcionCoctail = (TextView) findViewById(R.id.tv_descripcion);
        imagenCoctail = (ImageView) findViewById(R.id.iv_coctail);
        ingre1 = (TextView) findViewById(R.id.tv_ingre1);
        ingre2 = (TextView) findViewById(R.id.tv_ingre2);
        ingre3 = (TextView) findViewById(R.id.tv_ingre3);
        ingre4 = (TextView) findViewById(R.id.tv_ingre4);
        ingre5 = (TextView) findViewById(R.id.tv_ingre5);
        ingre6 = (TextView) findViewById(R.id.tv_ingre6);
        ingre7 = (TextView) findViewById(R.id.tv_ingre7);
        ingre8 = (TextView) findViewById(R.id.tv_ingre8);
        ingre9 = (TextView) findViewById(R.id.tv_ingre9);
        ingre10 = (TextView) findViewById(R.id.tv_ingre10);
        ingre11 = (TextView) findViewById(R.id.tv_ingre11);
        ingre12 = (TextView) findViewById(R.id.tv_ingre12);
        ingre13 = (TextView) findViewById(R.id.tv_ingre13);
        ingre14 = (TextView) findViewById(R.id.tv_ingre14);
        ingre15 = (TextView) findViewById(R.id.tv_ingre15);



        //Obtenemos los datos del intent del que proviene para cargarlos a la vista
        String nombre = getIntent().getStringExtra("coctail");
        int id = getIntent().getExtras().getInt("id");
        String desc = getIntent().getStringExtra("descripcion");
        String link = getIntent().getStringExtra("imagen");
        String ingredi1 = getIntent().getStringExtra("ingre1");
        String ingredi2 = getIntent().getStringExtra("ingre2");
        String ingredi3 = getIntent().getStringExtra("ingre3");
        String ingredi4 = getIntent().getStringExtra("ingre4");
        String ingredi5 = getIntent().getStringExtra("ingre5");
        String ingredi6 = getIntent().getStringExtra("ingre6");
        String ingredi7 = getIntent().getStringExtra("ingre7");
        String ingredi8 = getIntent().getStringExtra("ingre8");
        String ingredi9 = getIntent().getStringExtra("ingre9");
        String ingredi10 = getIntent().getStringExtra("ingre10");
        String ingredi11 = getIntent().getStringExtra("ingre11");
        String ingredi12 = getIntent().getStringExtra("ingre12");
        String ingredi13 = getIntent().getStringExtra("ingre13");
        String ingredi14 = getIntent().getStringExtra("ingre14");
        String ingredi15 = getIntent().getStringExtra("ingre15");
        String medida1 = getIntent().getStringExtra("medida1");
        String medida2 = getIntent().getStringExtra("medida2");
        String medida3 = getIntent().getStringExtra("medida3");
        String medida4 = getIntent().getStringExtra("medida4");
        String medida5 = getIntent().getStringExtra("medida5");
        String medida6 = getIntent().getStringExtra("medida6");
        String medida7 = getIntent().getStringExtra("medida7");
        String medida8 = getIntent().getStringExtra("medida8");
        String medida9 = getIntent().getStringExtra("medida9");
        String medida10 = getIntent().getStringExtra("medida10");
        String medida11 = getIntent().getStringExtra("medida11");
        String medida12 = getIntent().getStringExtra("medida12");
        String medida13 = getIntent().getStringExtra("medida13");
        String medida14 = getIntent().getStringExtra("medida14");
        String medida15 = getIntent().getStringExtra("medida15");

        //Cargamos los datos a la vista
        nombreCoctail.setText(nombre);
        idCoctail.setText("Código - "+String.valueOf(id));
        descripcionCoctail.setText(desc);

        //Los ingredientes y las medidas de cada ingrediente van aparte, con una sentencia if para
        //mostrarlos o no, dependiendo de si son null o no
        if(ingredi1 != null && !(ingredi1.equals("")))
        {

            ingre1.setText(ingredi1+" - "+medida1);
        } else {
            ingre1.setVisibility(View.GONE);
        }

        if(ingredi2 != null && !(ingredi2.equals("")))
        {
            ingre2.setText(ingredi2+" - "+medida2);
        } else {
            ingre2.setVisibility(View.GONE);
        }

        if(ingredi3 != null && !(ingredi3.equals("")))
        {
            ingre3.setText(ingredi3+" - "+medida3);
        } else {
            ingre3.setVisibility(View.GONE);
        }

        if(ingredi4 != null && !(ingredi4.equals("")))
        {
            ingre4.setText(ingredi4+" - "+medida4);
        } else {
            ingre4.setVisibility(View.GONE);
        }

        if(ingredi5 != null && !(ingredi5.equals("")))
        {
            ingre5.setText(ingredi5+" - "+medida5);
        } else {
            ingre5.setVisibility(View.GONE);
        }

        if(ingredi6 != null && !(ingredi6.equals("")))
        {
            ingre6.setText(ingredi6+" - "+medida6);
        } else {
            ingre6.setVisibility(View.GONE);
        }

        if(ingredi7 != null && !(ingredi7.equals("")))
        {
            ingre7.setText(ingredi7+" - "+medida7);
        } else {
            ingre7.setVisibility(View.GONE);
        }

        if(ingredi8 != null && !(ingredi8.equals("")))
        {
            ingre8.setText(ingredi8+" - "+medida8);
        } else {
            ingre8.setVisibility(View.GONE);
        }

        if(ingredi9 != null && !(ingredi9.equals("")))
        {
            ingre9.setText(ingredi9+" - "+medida9);
        } else {
            ingre9.setVisibility(View.GONE);
        }

        if(ingredi10 != null && !(ingredi10.equals("")))
        {
            ingre10.setText(ingredi10+" - "+medida10);
        } else {
            ingre10.setVisibility(View.GONE);
        }

        if(ingredi11 != null && !(ingredi11.equals("")))
        {
            ingre11.setText(ingredi11+" - "+medida11);
        } else {
            ingre11.setVisibility(View.GONE);
        }

        if(ingredi12 != null && !(ingredi12.equals("")))
        {
            ingre12.setText(ingredi12+" - "+medida12);
        } else {
            ingre12.setVisibility(View.GONE);
        }

        if(ingredi13 != null && !(ingredi13.equals("")))
        {
            ingre13.setText(ingredi13+" - "+medida13);
        } else {
            ingre13.setVisibility(View.GONE);
        }

        if(ingredi14 != null && !(ingredi14.equals("")))
        {
            ingre14.setText(ingredi14+" - "+medida14);
        } else {
            ingre14.setVisibility(View.GONE);
        }

        if(ingredi15 != null && !(ingredi15.equals("")))
        {
            ingre15.setText(ingredi15+" - "+medida15);
        } else {
            ingre15.setVisibility(View.GONE);
        }


        //Con la librería Picasso, cargamos las imágnenes del cóctel elegido
        Picasso.get().load(link).into(imagenCoctail);
    }
}