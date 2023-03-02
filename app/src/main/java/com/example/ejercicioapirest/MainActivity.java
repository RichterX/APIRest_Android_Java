package com.example.ejercicioapirest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
{
    ImageButton btnComida, btnCoctel;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asiganmos los botones a la vista
        btnComida = findViewById(R.id.btn_comida);
        btnCoctel = findViewById(R.id.btn_coctail);

        //Asiganmos los eventos a los botones para que nos lleve a la actividad correspondiente
        btnCoctel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, CoctailMain.class);
                startActivity(intent);
            }
        });

        btnComida.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(MainActivity.this, FoodMain.class);
                startActivity(intent);
            }
        });

    }
}